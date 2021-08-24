package land.tbp.land.tbp.youtube

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.api.client.auth.oauth2.*
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.*
import com.google.api.client.googleapis.util.Utils
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.util.store.DataStore
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.youtube.YouTubeScopes
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.bufferedReader

@Suppress("LocalVariableName", "PrivatePropertyName")
class GoogleAuthService(
    private val httpTransport: NetHttpTransport,
    private val googleCredentialsRepository: GoogleCredentialsRepository
) {
    private val JSON_FACTORY: JsonFactory = Utils.getDefaultJsonFactory()
    private val AUTH_SCOPES = mutableListOf(YouTubeScopes.YOUTUBE_READONLY, "https://www.googleapis.com/auth/userinfo.email")

    private val credentialDataStore: DataStore<StoredCredential> = FileDataStoreFactory(File("config/private")).getDataStore("oauthCredentials") // todo probably i should get rid of this data store, as i will persist the tokens in memory.

    val CLIENT_SECRETS = "C:/work/Youtube Subscriptions Mailer/config/private/client_secret.json" // todo from eng
    val googleClientSecret = GoogleClientSecrets.load(JSON_FACTORY, Paths.get(CLIENT_SECRETS).bufferedReader())

    @Suppress("RedundantSamConstructor")
    private val googleAuthorizationCodeFlow: GoogleAuthorizationCodeFlow by lazy {


        GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, googleClientSecret, AUTH_SCOPES)
            .setAccessType("offline")
            .setCredentialCreatedListener(AuthorizationCodeFlow.CredentialCreatedListener { _: Credential?, tokenResponse: TokenResponse? ->
                println("onTokenResponse")
                println(tokenResponse)

                if (tokenResponse is GoogleTokenResponse) {
                    val email = GoogleIdToken.parse(JSON_FACTORY, tokenResponse.idToken).payload.email
                    googleCredentialsRepository.newCredentials(GoogleCredentials(email, tokenResponse))
                }


            })
            .addRefreshListener(object : CredentialRefreshListener {
                override fun onTokenResponse(credential: Credential?, tokenResponse: TokenResponse?) {
                    println("onTokenResponse")
                    println(tokenResponse)

                    // TODO tbp: update credentials

                }

                override fun onTokenErrorResponse(credential: Credential?, tokenErrorResponse: TokenErrorResponse?) {
                    println("onTokenErrorResponse")
                    println(credential)
                    println(tokenErrorResponse)

                    // TODO tbp: what to do if refresh fails?
                }
            })
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
            .build()
            .setFromTokenResponse(tokenResponse)
    }
}


class GoogleCredentialsRepository {
    private val storage = Paths.get("C:/work/Youtube Subscriptions Mailer/config/private/credentials.json")

    private val credentials: MutableMap<String, GoogleCredentials> by lazy {

        val credentialsFromPersistence: MutableMap<String, GoogleCredentials> = jacksonObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS).readValue(storage.toFile())
        credentialsFromPersistence

//        val credentialsFromPersistence: MutableSet<GoogleCredentials> = jacksonObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS).readValue(storage.toFile())
//        if (credentialsFromPersistence.isNotEmpty()) {
//            credentialsFromPersistence
//        } else {
//        mutableMapOf()
//        }
    }

    private val JSON_FACTORY: JsonFactory = Utils.getDefaultJsonFactory()


    fun newCredentials(googleCredentials: GoogleCredentials) {
        credentials[googleCredentials.email] = googleCredentials
        dumpCredentials()


    }

    private fun dumpCredentials() {
        val json = jacksonObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsBytes(credentials)
        Files.write(storage, json)
    }

    fun getByUser(user: String): TokenResponse {
        return credentials[user]!!.googleTokenResponse
    }
}

data class GoogleCredentials(
    val email: String,
    val googleTokenResponse: GoogleTokenResponse
)
