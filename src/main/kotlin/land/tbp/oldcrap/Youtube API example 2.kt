package land.tbp.land.tbp.oldcrap


/**
 * Explanation for Parts and Fields: https://developers.google.com/youtube/v3/getting-started#partial
 *
 * For testing, i want
 * - parts: all (i think i have to type the parts manually, but maybe there's an "ALL" value)
 * - fields: `*`
 *
 * TODO: try to use etags to maximize performance, if possible: https://developers.google.com/youtube/v3/getting-started#performance
 */

//
//val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
////val googleAuthService = GoogleAuthService(httpTransport, GoogleCredentialsRepository())
//val youtubeService = YoutubeService(httpTransport)
//
//
//
//fun main() {
////    newUser(ChannelIDs.Subscriptions.public1)
//    newUser(ChannelIDs.Subscriptions.private)
//
////    existingUser("cristian.nahsuc@gmail.com")
//    existingUser("cristian@tbp.land")
//
//}
//
//fun existingUser(user: String) {
//    val credential = googleAuthService.forUser(user)
//    val youtube: YouTube = youtubeService.createForUser(credential)
//
//    val listRequest: HttpRequest = youtube.subscriptions()
//        .list(listOf("snippet","contentDetails"))
//        .setPrettyPrint(true)
//        .setMine(true)
//        .setFields("*")
//        .buildHttpRequest()
//
//    youtube.batch()
//        .queue(listRequest, SubscriptionListResponse::class.java, GoogleJsonErrorContainer::class.java,object : JsonBatchCallback<SubscriptionListResponse>() {
//            override fun onSuccess(s: SubscriptionListResponse, responseHeaders: HttpHeaders?) {
//                println(s)
//                println(s.pageInfo.totalResults)
//                println()
//                println()
//            }
//
//            override fun onFailure(e: GoogleJsonError, responseHeaders: HttpHeaders) {
//                error(e) // todo no idea what to do in case of error `¯\_(ツ)_/¯`
//            }
//        })
//        .execute()
//}
//
//private fun newUser(channelId: String) {
//    val credential = googleAuthService.authorizeNewUser()
//    val youtube: YouTube = youtubeService.createForUser(credential)
//
//    val subscriptions: SubscriptionListResponse = youtube.subscriptions()
//        .list(listOf("snippet","contentDetails"))
//        .setPrettyPrint(true)
//        .setMine(true)
//        .setFields("*")
//        .execute()
//
//    println(subscriptions)
//    println(subscriptions.pageInfo.totalResults)
//    println()
//    println()
//}
//
//
//
//
//
//object ChannelIDs {
//    object Subscriptions {
//        const val public1 = "UC5cw1VFE1rsZbN9Q2cA40aw"
//        const val public2 = "UCdCaX6gDODXvM5r7bHickww"
//        const val private = "UC2Zm9eTC_AaM040N98mRIrA"
//    }
//}
