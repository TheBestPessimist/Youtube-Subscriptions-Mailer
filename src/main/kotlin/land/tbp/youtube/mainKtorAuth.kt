package land.tbp.land.tbp.youtube

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.util.Utils
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import java.nio.file.Paths
import kotlin.io.path.bufferedReader

val CLIENT_SECRETS = "C:/work/Youtube Subscriptions Mailer/config/private/client_secret.json" // todo from env
val googleClientSecret = GoogleClientSecrets.load(Utils.getDefaultJsonFactory(), Paths.get(CLIENT_SECRETS).bufferedReader())


fun main() {
    embeddedServer(Netty, port = 8080) {
        youtubeModule()
    }.start(wait = true)
}


fun Application.youtubeModule() {
    install(Authentication) {
        oauth("GoogleOAuth") {
            urlProvider = { "http://localhost:8080/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = googleClientSecret.details.authUri,
                    accessTokenUrl = googleClientSecret.details.tokenUri,
                    requestMethod = HttpMethod.Post,
                    clientId = googleClientSecret.details.clientId,
                    clientSecret = googleClientSecret.details.clientSecret,
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
                    nonceManager = StatelessHmacNonceManager("1".toByteArray()) // todo: is this safe since "1" is hardcoded? no idea.
                    // Technical note: access_type=offline, which is mandatory for any sort of app is not present in this request, however the refresh_token is still returned by google. Should I cross my fingers that this is enough?
                )
            }
            client = HttpClient(Apache) {
                install(JsonFeature) {
                    serializer = JacksonSerializer()
                }
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }
    }

    routing {
        authenticate("GoogleOAuth") {
            get("/login") {
                // Redirects to 'authorizeUrl' automatically
            }

            get("/callback") {
                val principal: OAuthAccessTokenResponse.OAuth2 = call.principal()!!
                principal.accessToken
                principal.refreshToken!! //<- this must not be null!
                println()
                println()
                println()
                println(principal)
//                call.sessions.set(UserSession(principal?.accessToken.toString()))
                call.respond("Thank you for authenticating with us. Your credentials are safe with us :^)")
//                call.respondRedirect("/hello") // todo. i definitely wanna redirect, because otherwise the URL will contain the secrects and all the other crap
            }
        }
    }
}
