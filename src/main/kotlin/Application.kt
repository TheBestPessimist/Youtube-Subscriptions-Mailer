package land.tbp

import com.fasterxml.jackson.databind.SerializationFeature
import com.google.api.services.youtube.YouTubeScopes
import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.parsers.PropsParser
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import land.tbp.land.tbp.config.Config
import land.tbp.land.tbp.youtube.googleOAuth
import org.slf4j.event.Level
import java.io.File

val AUTH_SCOPES = mutableListOf(YouTubeScopes.YOUTUBE_READONLY, "https://www.googleapis.com/auth/userinfo.email")

val config: Config = ConfigLoader.Builder()
    .addFileExtensionMapping("env", PropsParser())
    .build()
    .loadConfigOrThrow(
        File("""C:/work/Youtube Subscriptions Mailer/.env"""),
    )


fun main() {
    System.setProperty("io.ktor.development", "true")
    System.setProperty("org.jooq.no-logo", "true");

    val embeddedServer: NettyApplicationEngine = embeddedServer(Netty, 6969, watchPaths = listOf("classes", "resources")) {
//        dummyModule()
        googleOAuth()
    }

    embeddedServer.start(false)

//    subscribble()
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.dummyModule(testing: Boolean = false) {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

    install(AutoHeadResponse)

    install(CallLogging) {
        level = Level.INFO
//        filter { call -> call.request.path().startsWith("/") }
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }


//            // todo install this from the test data
//        jackson(ContentType.Application.Xml) {
//            enable(SerializationFeature.INDENT_OUTPUT)
//////            this.
////            XmlMapper()
////            jacksonObjectMapper().
//        }

    }



    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            val client = HttpClient(Apache) {
                install(HttpTimeout) {
                }
//                install(ContentNegotiation) {
//                    jackson {
//                        enable(SerializationFeature.INDENT_OUTPUT)
//                    }
//                }
                install(Logging) {
                    level = LogLevel.HEADERS
                }
                BrowserUserAgent() // install default browser-like user-agent
                // install(UserAgent) { agent = "some user agent" }
            }
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

//        installPubSubHubbubRoutes()
    }
}

data class JsonSampleClass(val hello: String)
