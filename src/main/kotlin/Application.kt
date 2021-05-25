package land.tbp

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import land.tbp.pubSubHubbub.installPubSubHubbubRoutes
import land.tbp.pubSubHubbub.subscribble
import org.slf4j.event.Level

fun main(args: Array<String>): Unit {
    val embeddedServer: NettyApplicationEngine = embeddedServer(Netty, 6969) {

        module()
    }

    embeddedServer.start(false)

    subscribble()
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
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

    install(Authentication) {
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
                install(JsonFeature) {
                    serializer = GsonSerializer()
                }
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

        installPubSubHubbubRoutes()
    }
}

data class JsonSampleClass(val hello: String)
