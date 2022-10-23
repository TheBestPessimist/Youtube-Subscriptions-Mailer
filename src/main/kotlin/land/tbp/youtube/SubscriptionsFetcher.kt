package land.tbp.land.tbp.youtube

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.youtube.YouTube
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.UserCredentials
import land.tbp.jooq.tables.pojos.YoutubeChannel
import land.tbp.land.tbp.db.UserRepository
import land.tbp.land.tbp.util.logger

class SubscriptionsFetcher(
    private val email: String,
    private val googleClientId: String,
    private val googleClientSecret: String,
    private val userRepository: UserRepository,
) {
    companion object {
        private val log = logger()
    }

    /*
     TODO tbp: better logic: first check the number of subscriptions:
         - if they're the same: nothing to sync
         - if they're changed: do the full sync
        Maybe this check can be done by writing another function? dunno at this point
     */

    fun fetchAll(): List<YoutubeChannel> {
        val refreshToken = userRepository.fetchRefreshTokenByEmail(email)
        val userCredentials = UserCredentials.newBuilder()
            .setClientId(googleClientId)
            .setClientSecret(googleClientSecret)
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

        val googleUserId = userRepository.fetchByEmail(email).single().googleUserId!!

        val request = y.subscriptions()
            .list(listOf("snippet", "contentDetails"))
//            .setOrder() // TODO WHAT THE FUCK IS THE ORDER, YOUTUBE? the documentation makes no sense: https://developers.google.com/youtube/v3/docs/subscriptions/list
            .setQuotaUser(googleUserId) //see https://cloud.google.com/apis/docs/capping-api-usage#limiting_requests_per_user
            .setMaxResults(5000)
            .setMine(true)
            .setFields("*")
            .setPrettyPrint(true)

        val result = buildList {
            var pageToken: String? = null
            do {
                request.pageToken = pageToken
                val response = request.execute()
                val channels = response.items.map {
                    YoutubeChannel(null, it.snippet.resourceId.channelId, it.snippet.title)
                }
                addAll(channels)
                pageToken = response.nextPageToken
                log.info(response.toPrettyString())
            } while (pageToken != null)
        }
        return result
    }
}
