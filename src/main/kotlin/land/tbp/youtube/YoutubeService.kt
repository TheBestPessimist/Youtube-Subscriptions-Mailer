package land.tbp.land.tbp.youtube

import land.tbp.jooq.tables.daos.YoutubeChannelDao
import land.tbp.jooq.tables.pojos.Subscription
import land.tbp.jooq.tables.pojos.YoutubeChannel
import land.tbp.jooq.tables.references.SUBSCRIPTION
import land.tbp.jooq.tables.references.YOUTUBE_CHANNEL
import land.tbp.land.tbp.config.Components
import land.tbp.land.tbp.config.Components.userRepository
import land.tbp.land.tbp.config.config
import land.tbp.land.tbp.db.dslContext

const val APPLICATION_NAME = "API code samples" // todo should this be an application property?

// TODO 1 add stuff here
// TODO 2 tbp: add some tests using MockTokenServerTransport for the various auth problems which might occur
class YoutubeService() {

}


fun main() {
//    val email = "cristian.nahsuc@gmail.com"
    val email = "cristian@tbp.land"

    val subscriptions = SubscriptionsFetcher(
        email,
        config.googleCredentials.clientId,
        config.googleCredentials.clientSecret,
        Components.userRepository
    ).fetchAll().also { println(it) }

    val googleUserId = userRepository.fetchByEmail(email).single().googleUserId!!



    dslContext.transaction { ctx ->
        ctx.dsl().deleteFrom(YOUTUBE_CHANNEL).execute()

        val ids = subscriptions.map {
            ctx.dsl().newRecord(YOUTUBE_CHANNEL, it).apply { store() }.youtubeChannelId!!
        }

        val userId = userRepository.fetchByEmail(email).single().userId!!
        ids.forEach { channelId ->
            ctx.dsl().newRecord(SUBSCRIPTION, Subscription(userId, channelId)).store()
        }


        println("inside trx")
        val findAll2: MutableList<YoutubeChannel> = YoutubeChannelDao(ctx).findAll()
        println(findAll2)
    }

     // TODO tbp: need to make tests:
    //          new subscription added
    //          subscription deleted
    //          subscription unchanged
    //          can be 1 test, can be multiple?
    //          obviously, need to implement that, but it's faster to do it via test than via clicking in youtube.
    //              plus... i _do_ need my tests


    val snippetChannelId = "UC5cw1VFE1rsZbN9Q2cA40aw" // it appears this one is mine
    val resourceIdChannelId = "UCXuqSBlHAE6Xw-yeJA0Tunw" // it appears this is the target

//    LTT uploads playlst = UUXuqSBlHAE6Xw-yeJA0Tunw
//    channel.contentdetails.uploads
//// https://developers.google.com/youtube/v3/docs/playlistItems/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%22%5D%2C%22maxResults%22%3A25%2C%22playlistId%22%3A%22UUXuqSBlHAE6Xw-yeJA0Tunw%22%7D#usage
//    y.videos()
//        .
}
