package land.tbp.db

import com.google.api.client.auth.oauth2.BearerToken
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants
import com.google.api.client.googleapis.batch.json.JsonBatchCallback
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.json.GoogleJsonError
import com.google.api.client.googleapis.json.GoogleJsonErrorContainer
import com.google.api.client.googleapis.util.Utils
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpHeaders
import com.google.api.client.http.HttpRequest
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SubscriptionListResponse
import io.ktor.auth.*
import land.tbp.config
import land.tbp.land.tbp.youtube.YoutubeService

private val youtubeService = YoutubeService(GoogleNetHttpTransport.newTrustedTransport())

object GoogleAuthRepo {
    fun persistTokenResponse(principal: OAuthAccessTokenResponse.OAuth2) {
        val tokenResponse = TokenResponse().apply {
            accessToken = principal.accessToken
            tokenType = principal.tokenType
            expiresInSeconds = principal.expiresIn
            refreshToken = principal.refreshToken
            scope = principal.extraParameters["scope"]
        }
        authenticatedUsers.add(tokenResponse)
    }

    val authenticatedUsers = mutableListOf<TokenResponse>()
}

fun fdsa(): Unit {
    GoogleAuthRepo.authenticatedUsers.forEach { tokenResponse ->
        val credential = createCredentialWithRefreshToken(tokenResponse)
        val youtube: YouTube = youtubeService.createForUser(credential)

        val listRequest: HttpRequest = youtube.subscriptions()
            .list(listOf("snippet","contentDetails"))
            .setPrettyPrint(true)
            .setMine(true)
            .setFields("*")
            .buildHttpRequest()

        youtube.batch()
            .queue(listRequest, SubscriptionListResponse::class.java, GoogleJsonErrorContainer::class.java,object : JsonBatchCallback<SubscriptionListResponse>() {
                override fun onSuccess(s: SubscriptionListResponse, responseHeaders: HttpHeaders?) {
                    println(s)
                    println(s.pageInfo.totalResults)
                    println()
                    println()
                }

                override fun onFailure(e: GoogleJsonError, responseHeaders: HttpHeaders) {
                    error(e) // todo no idea what to do in case of error `¯\_(ツ)_/¯`
                }
            })
            .execute()

    }
}

fun createCredentialWithRefreshToken(tokenResponse: TokenResponse?): Credential {
    return Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
        .setTransport(GoogleNetHttpTransport.newTrustedTransport())
        .setJsonFactory(Utils.getDefaultJsonFactory())
        .setTokenServerUrl(GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL))
        .setClientAuthentication(ClientParametersAuthentication(config.googleCredentials.clientId, config.googleCredentials.clientSecret))
//        .addRefreshListener(credentialRefreshListener)
        .build()
        .setFromTokenResponse(tokenResponse)
}
