package land.tbp.land.tbp.youtube

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.util.Utils
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.services.youtube.YouTube

class YoutubeService(private val httpTransport: NetHttpTransport) {
    private val APPLICATION_NAME = "API code samples"
    private val JSON_FACTORY: JsonFactory = Utils.getDefaultJsonFactory()

    fun createForUser(credential: Credential): YouTube {
        return YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build()
    }
}
