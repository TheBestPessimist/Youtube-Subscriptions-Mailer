package land.tbp.pubSubHubbub


val path = "/pshb-webhook"


//fun Routing.installPubSubHubbubRoutes() {
//    val log = logger()
//
////        hub.mode =
////            [subscribe], hub.topic = [https://superfeedr-blog-feed.herokuapp.com/], hub.challenge=[0yrw79js7wyjj7b3ayvi], hub.lease_seconds=[1296000]}
//
//
//    println("installing pshb")
//
//    // get used for subscription verification
//    get(path) {
//        println("   raw params: ${call.parameters}")
//
//        println("   headers: ${call.request.headers.toMap()}")
//        val pshbParams = PSHBSubscribeParams.of(call.parameters)
//
//        println("   " + pshbParams)
//        call.respondText(pshbParams.challenge, status = HttpStatusCode.OK)
//    }
//
//    // post used for topic updates
//    post(path) {
//        println("POST \n\n\n\n")
//        println("   raw params: ${call.parameters}")
//
//        println("   headers: ${call.request.headers.toMap()}")
//        val a = call.receive<String>()
//        println(a)
//        call.respond(HttpStatusCode.OK)
//    }
//
//}
//
//private data class PSHBSubscribeParams(
//    val mode: String,
//    val topic: String,
//    val challenge: String,
//    val leaseSeconds: Duration
//) {
//    companion object {
//        fun of(params: Parameters): PSHBSubscribeParams {
//            return PSHBSubscribeParams(
//                params["hub.mode"]!!,
//                params["hub.topic"]!!,
//                params["hub.challenge"]!!,
//                Duration.ofSeconds(params["hub.lease_seconds"]!!.toLong())
//            )
//        }
//    }
//}
//
//
//fun newPSHBClient(): HttpClient = HttpClient(Apache) {
//    install(HttpTimeout) {
//    }
////        install(JsonFeature) {
////            serializer = JacksonSerializer(objectMapperSettings)
////        }
//
////        install(JsonFeature) {
////            serializer = GsonSerializer {
////                setPrettyPrinting()
////                disableHtmlEscaping()
////            }
////        }
//
//    install(Logging) {
//        level = LogLevel.ALL
//    }
////        BrowserUserAgent() // install default browser-like user-agent
//    install(UserAgent) { agent = "TBP PubSubHubbub Client" }
//}
//
//fun subscribble() {
//    data class PSHB(
//        val verify: String,
//        val topic: String,
//        val callback: String,
//        val mode: String,
//        val secret: String? = null
//    )
//
//    data class PSHBSubscribeRequest(val hub: PSHB)
//
//    runBlocking {
//        println("Before Subscription")
//
//        val pshbSubscribeRequest = PSHB(
//            verify = "sync",
//            topic = "http://push-pub.appspot.com/feed",
//            callback = "https://5f4dde5bacdf.ngrok.io$path",
//            mode = "subscribe"
//        )
//
//        val client = newPSHBClient()
//        val response = client.post<String> {
//            url("https://pubsubhubbub.superfeedr.com/")
//            contentType(ContentType.Application.FormUrlEncoded)
//            body = listOf(
//                "hub.verify" to pshbSubscribeRequest.verify,
//                "hub.topic" to pshbSubscribeRequest.topic,
//                "hub.callback" to pshbSubscribeRequest.callback,
//                "hub.mode" to pshbSubscribeRequest.mode
//            ).formUrlEncode()
//        }
//
//        println("After Subscription")
//        println("response: $response")
//    }
//}
