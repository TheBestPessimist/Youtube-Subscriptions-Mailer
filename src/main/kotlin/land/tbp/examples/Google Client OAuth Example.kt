package land.tbp.land.tbp.examples

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.auth.*
import land.tbp.land.tbp.config.config
import land.tbp.land.tbp.youtube.ErrorInfo
import land.tbp.land.tbp.youtube.GoogleUserInfo


/*
Documentation here: https://ktor.io/docs/bearer-client.html#oauth-flow
 */
suspend fun main() {

    val bearerTokenStorage = mutableListOf(
        BearerTokens(
            "", "" // when trying this, please get the access token and refresh token beforehand
        )
    )

    val client = HttpClient(Apache) {
        install(Logging) { level = LogLevel.ALL }
        install(ContentNegotiation) {
            jackson {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            }
        }
        install(Auth) {
            bearer {
                loadTokens { bearerTokenStorage.last() }
                /*
                Technical: By default, the client will not send the credentials on the first request.
                Instead, the client makes the very first request unauthenticated.
                - if it receives a 401 (Unauthorized), then it sends the authorization in every subsequent request
                - if it does not receive a 401, then it will not send the authorization and continue making requests unauthorized

                Therefore Ktor has added the feature `sendWithoutRequest`, which returns a boolean:
                 - Should I send the auth credentials when making this particular request? yes, no
                If we return true from the lambda, then we send the credentials to ALL requests and ALL domains.
                If we do a check like `it:Request -> request.url.host == "www.googleapis.com"`, this means we return true only for google APIs
                => we send credentials only for googleapis, and not for any other domains.
                Why has Ktor decided to not auth by default, I don't know, but at least you now know what this function does :^).
                 */
                sendWithoutRequest { true }
                refreshTokens {
//                    println("Will refresh token")
                    val newAccessToken = client.submitForm(
                        url = "https://accounts.google.com/o/oauth2/token",
                        formParameters = Parameters.build {
                            append("grant_type", "refresh_token")
                            append("client_id", config.googleCredentials.clientId)
                            append("client_secret", config.googleCredentials.clientSecret)
                            append("refresh_token", oldTokens?.refreshToken ?: "")
                        },
                        block = { markAsRefreshTokenRequest() }
                    )
                        .body<OAuthAccessTokenResponse.OAuth2>()

//                    println("newAccessToken: $newAccessToken")
                    bearerTokenStorage.add(BearerTokens(newAccessToken.accessToken, oldTokens?.refreshToken!!))
                    bearerTokenStorage.last()
                }
            }
        }
    }

    val response: HttpResponse = client.get("https://www.googleapis.com/oauth2/v2/userinfo")
    try {
        val userInfo: GoogleUserInfo = response.body()
        println("UserInfo response: $userInfo")
        println("Hello, ${userInfo.name}!")
    } catch (e: Exception) {
        val errorInfo: ErrorInfo = response.body()
        println(errorInfo.error.message)
    }
}
