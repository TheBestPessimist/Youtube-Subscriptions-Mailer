package land.tbp.land.tbp.youtube

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import land.tbp.AUTH_SCOPES
import land.tbp.config
import land.tbp.db.GoogleAuthRepo
import land.tbp.land.tbp.db.OAuth2TokenRepository
import land.tbp.land.tbp.db.UserRepository

//import land.tbp.land.tbp.db.UserRepository

/*
http://localhost:6969/login

 */
fun Application.googleOAuth() {
    val googleHttpClient = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = JacksonSerializer() {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            }
        }
        install(Logging) { level = LogLevel.ALL }
    }

//    install(ContentNegotiation) {
//        jackson {
//            enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
//        }
//    }

    install(Authentication) {
        oauth("GoogleOAuth") {
            urlProvider = { "http://localhost:6969/callback" } // todo how not to hardcode?
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://oauth2.googleapis.com/token",
                    requestMethod = HttpMethod.Post,
                    clientId = config.googleCredentials.clientId,
                    clientSecret = config.googleCredentials.clientSecret,
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile") + AUTH_SCOPES,
                    authorizeUrlInterceptor = {
                        /*
                        Technical:
                        `access_type=offline` is mandatory to receive the refresh_token from google.
                        Workaround for https://youtrack.jetbrains.com/issue/KTOR-2128
                         */
                        this.parameters["access_type"] = "offline"
                    },
//                    nonceManager = StatelessHmacNonceManager("1".toByteArray()) // todo: is this safe since "1" is hardcoded? no idea.
                )
            }
            client = googleHttpClient
        }
    }

    install(Sessions) {
        cookie<UserCookie>("user_cookie")
    }

    routing {
        authenticate("GoogleOAuth") {
            get("/login") {
                // Redirects to 'authorizeUrl' automatically
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2 = call.principal()!!
                principal.refreshToken!! //<- this must not be null!
                log.info(principal.toString())

                call.sessions.set(UserCookie(principal.accessToken))
                GoogleAuthRepo.persistTokenResponse(principal)

                saveUserAndAuthToken(principal, googleHttpClient)

                call.respondRedirect("/authentication-successful")
            }
        }

        get("/authentication-successful") {
//            fdsa()

            // todo login is finished here. Now we're left to persist the user, its email and the auth token

            val cookie = call.sessions.get<UserCookie>()!!
            val userInfo = googleHttpClient.get<GoogleUserInfo>("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${cookie.token}")
                }
            }

            call.respond(userInfo.toString())
        }
    }


}

suspend fun saveUserAndAuthToken(principal: OAuthAccessTokenResponse.OAuth2, googleHttpClient: HttpClient) {
    val userInfo = googleHttpClient.get<GoogleUserInfo>("https://www.googleapis.com/oauth2/v2/userinfo") {
        headers {
            append(HttpHeaders.Authorization, "Bearer ${principal.accessToken}")
        }
    }

    val userRecord = UserRepository().upsert(userInfo)
    OAuth2TokenRepository().upsert(principal, userRecord.userId!!)
}

data class UserCookie(val token: String)

data class GoogleUserInfo(
    @JsonProperty("id")
    val googleUserId: String,
    val email: String,
    val name: String,
    val picture: String?,
)