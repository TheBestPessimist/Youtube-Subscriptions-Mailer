package land.tbp.land.tbp.youtube

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.youtube.YouTube
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.UserCredentials
import land.tbp.land.tbp.config.config
import land.tbp.land.tbp.db.UserRepository

private const val APPLICATION_NAME = "API code samples"

// TODO 1 add stuff here
// TODO 2 tbp: add some tests using MockTokenServerTransport for the various auth problems which might occur
class YoutubeService() {

}


fun main() {
    val refreshToken = UserRepository.INSTANCE.fetchRefreshTokenByEmail("cristian.nahsuc@gmail.com")
    val userCredentials = UserCredentials.newBuilder()
        .setClientId(config.googleCredentials.clientId)
        .setClientSecret(config.googleCredentials.clientSecret)
        .setRefreshToken(refreshToken)
        .build()
    val httpRequestInitializer = HttpCredentialsAdapter(userCredentials)

    val y = YouTube.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        GsonFactory.getDefaultInstance(),
        httpRequestInitializer
    )
        .setApplicationName(APPLICATION_NAME)
        .build()

//    println(userCredentials)
//    println(userCredentials.refreshAccessToken())

    val request = y.subscriptions()
        .list(listOf("snippet", "contentDetails"))
        .setMine(true)
        .setFields("*")
        .setPrettyPrint(true)
        .execute()

    println(request.toPrettyString())


    val snippetChannelId = "UC5cw1VFE1rsZbN9Q2cA40aw" // it appears this one is mine
    val resourceIdChannelId = "UCXuqSBlHAE6Xw-yeJA0Tunw" // it appears this is the target

//    LTT uploads playlst = UUXuqSBlHAE6Xw-yeJA0Tunw
//    channel.contentdetails.uploads
//// https://developers.google.com/youtube/v3/docs/playlistItems/list?apix_params=%7B%22part%22%3A%5B%22snippet%2CcontentDetails%22%5D%2C%22maxResults%22%3A25%2C%22playlistId%22%3A%22UUXuqSBlHAE6Xw-yeJA0Tunw%22%7D#usage
//    y.videos()
//        .
}
