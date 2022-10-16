package land.tbp

import com.fasterxml.jackson.databind.SerializationFeature
import com.google.api.services.youtube.YouTubeScopes
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*
import land.tbp.land.tbp.youtube.UserCookie
import land.tbp.land.tbp.youtube.googleOAuthModule
import org.slf4j.event.Level




fun main() {
    System.setProperty("io.ktor.development", "true")
    System.setProperty("org.jooq.no-logo", "true");

    val embeddedServer: NettyApplicationEngine =
        embeddedServer(Netty, 6969, watchPaths = listOf("classes", "resources")) {
//        dummyModule()
            googleOAuthModule()
            homePageModule()
        }

    embeddedServer.start(true)

//    subscribble()
}

fun Application.homePageModule() {
    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Head.Title.YoutubeMailer")
//                    { below is an alternative to the above
//                        "Head.Title".unaryPlus()
//                    }
                }
                body {
                    h1 { +"Body.H1." }
                    p { +"Sample text" }

                    p { a("/login") { +"/login endpoint" } }
                    p { a("/callback") { +"/callback endpoint" } }
                }
            }
        }

        get("/do-i-have-a-cookie-here") {
            val cookie = call.sessions.get<UserCookie>()!!
            call.respondHtml {

            }
        }
    }
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.dummyModule(testing: Boolean = false) {
//    install(Compression) {
//        gzip {
//            priority = 1.0
//        }
//        deflate {
//            priority = 10.0
//            minimumSize(1024) // condition
//        }
//    }

    install(AutoHeadResponse)

    install(CallLogging) {
        level = Level.INFO
//        filter { call -> call.request.path().startsWith("/") }
    }

//    install(DefaultHeaders) {
//        header(HttpHeaders.Server, "Ktor") // will send this header with each response
//    }

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
