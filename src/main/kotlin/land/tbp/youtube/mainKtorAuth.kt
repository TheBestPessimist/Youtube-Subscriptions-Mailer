package land.tbp.land.tbp.youtube

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
import land.tbp.config


fun main() {
    System.setProperty("io.ktor.development", "true")
    val embeddedServer: NettyApplicationEngine = embeddedServer(Netty, 6969, watchPaths = listOf("classes", "resources")) {

        googleOAuth()
    }.start(wait = true)
}

/*
http://localhost:6969/login

 */
fun Application.googleOAuth() {
    install(Authentication) {
        oauth("GoogleOAuth") {
            urlProvider = { "http://localhost:6969/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://oauth2.googleapis.com/token",
                    requestMethod = HttpMethod.Post,
                    clientId = config.googleCredentials.clientId,
                    clientSecret = config.googleCredentials.clientSecret,
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
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
//                call.respondRedirect("/hello") // todo. i definitely wanna redirect, because otherwise the URL will contain the secrets and all the other crap
            }
        }
    }
}
