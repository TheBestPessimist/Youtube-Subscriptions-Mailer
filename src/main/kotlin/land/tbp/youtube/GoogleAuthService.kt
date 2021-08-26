package land.tbp.land.tbp.youtube

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.auth.oauth2.*
import com.google.api.client.auth.openidconnect.IdTokenResponse
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.*
import com.google.api.client.googleapis.util.Utils
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.youtube.YouTubeScopes
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.bufferedReader
import kotlin.io.path.exists

@Suppress("LocalVariableName", "PrivatePropertyName")
class GoogleAuthService(
    private val httpTransport: NetHttpTransport,
    private val googleCredentialsRepository: GoogleCredentialsRepository
) {
    private val JSON_FACTORY: JsonFactory = Utils.getDefaultJsonFactory()
    private val AUTH_SCOPES = mutableListOf(YouTubeScopes.YOUTUBE_READONLY, "https://www.googleapis.com/auth/userinfo.email")

    val CLIENT_SECRETS = "C:/work/Youtube Subscriptions Mailer/config/private/client_secret.json" // todo from eng
    val googleClientSecret = GoogleClientSecrets.load(JSON_FACTORY, Paths.get(CLIENT_SECRETS).bufferedReader())

    private val credentialRefreshListener: CredentialRefreshListener by lazy {
        object : CredentialRefreshListener {
            override fun onTokenResponse(credential: Credential?, tokenResponse: TokenResponse) {
                println("onTokenResponse")
                println(tokenResponse)

                val idToken: String? = tokenResponse.unknownKeys["id_token"] as String?
                if (idToken.isNullOrBlank()) {
                    error("The token is not an ${IdTokenResponse::class.simpleName} therefore there is no email available. Something unexpected happened.")
                }

                val email = GoogleIdTokenVerifier(GooglePublicKeysManager(httpTransport, JSON_FACTORY)).verify(idToken).payload.email
                googleCredentialsRepository.updateCredentials(GoogleCredentials(email, tokenResponse))
            }

            override fun onTokenErrorResponse(credential: Credential?, tokenErrorResponse: TokenErrorResponse?) {
                println("onTokenErrorResponse")
                println(credential)
                println(tokenErrorResponse)

                // TODO tbp: what to do if refresh fails?
            }
        }
    }

    @Suppress("DuplicatedCode")
    private val credentialCreatedListener: AuthorizationCodeFlow.CredentialCreatedListener = AuthorizationCodeFlow.CredentialCreatedListener { _: Credential?, tokenResponse: TokenResponse ->
        println("onTokenResponse")
        println(tokenResponse)

        if (tokenResponse is GoogleTokenResponse) {
            val email = GoogleIdToken.parse(JSON_FACTORY, tokenResponse.idToken).payload.email
            googleCredentialsRepository.newCredentials(GoogleCredentials(email, tokenResponse))
        } else {
            val idToken: String? = tokenResponse.unknownKeys["id_token"] as String?
            if (idToken.isNullOrBlank()) {
                error("The token is not an ${IdTokenResponse::class.simpleName} therefore there is no email available. Something unexpected happened.")
            }

            val email = GoogleIdTokenVerifier(GooglePublicKeysManager(httpTransport, JSON_FACTORY)).verify(idToken).payload.email
            googleCredentialsRepository.updateCredentials(GoogleCredentials(email, tokenResponse))
        }
    }

    @Suppress("RedundantSamConstructor")
    private val googleAuthorizationCodeFlow: GoogleAuthorizationCodeFlow by lazy {


        GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, googleClientSecret, AUTH_SCOPES)
            .setAccessType("offline")
            .setCredentialCreatedListener(credentialCreatedListener)
            .addRefreshListener(credentialRefreshListener)
            .build()
    }

    fun authorizeNewUser(): Credential {
        return AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, LocalServerReceiver()).authorize("")
    }

    fun forUser(user: String): Credential {
        val tokenResponse: TokenResponse = googleCredentialsRepository.getByUser(user)
        return createCredentialWithRefreshToken(tokenResponse)
    }


    private fun createCredentialWithRefreshToken(tokenResponse: TokenResponse?): Credential {
        return Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
            .setTransport(httpTransport)
            .setJsonFactory(JSON_FACTORY)
            .setTokenServerUrl(GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL))
            .setClientAuthentication(ClientParametersAuthentication(googleClientSecret.details.clientId, googleClientSecret.details.clientSecret))
            .addRefreshListener(credentialRefreshListener)
            .build()
            .setFromTokenResponse(tokenResponse)
    }
}


class GoogleCredentialsRepository {
    private val storageFile = Paths.get("C:/work/Youtube Subscriptions Mailer/config/private/credentials.json")

    private val credentials: MutableMap<String, GoogleCredentials> by lazy {
        if (storageFile.exists()) {
            val credentialsFromPersistence: MutableMap<String, GoogleCredentials> = jacksonObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS).readValue(storageFile.toFile())
            credentialsFromPersistence
        } else {
            mutableMapOf()
        }
    }

    fun newCredentials(googleCredentials: GoogleCredentials) {
        credentials[googleCredentials.email] = googleCredentials
        dumpCredentials()
    }

    /**
     * Technical: on `access_token` refresh, google does not return `refresh_token`,
     * therefore we cannot replace the old token with the current one.
     *
     * Instead, we must update only `access_token` and `expires_in_seconds` and keep the other fields untouched.
     *
     * Although the documentation is here: [https://developers.google.com/identity/protocols/oauth2/web-server],
     * this particular aspect is not explained.
     */
    fun updateCredentials(googleCredentials: GoogleCredentials) {
        getByUser(googleCredentials.email).apply {
            accessToken = googleCredentials.tokenResponse.accessToken
            expiresInSeconds = googleCredentials.tokenResponse.expiresInSeconds
        }
        dumpCredentials()
    }

    private fun dumpCredentials() {
        val json = jacksonObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(credentials)
        Files.write(storageFile, json)
    }

    fun getByUser(user: String): TokenResponse {
        return credentials[user]!!.tokenResponse
    }
}

data class GoogleCredentials(
    val email: String,
    val tokenResponse: TokenResponse
)
