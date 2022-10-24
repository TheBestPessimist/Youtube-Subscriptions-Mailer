package land.tbp.youtube

import com.google.api.client.googleapis.testing.auth.oauth2.MockGoogleCredential
import com.google.api.client.testing.http.MockHttpTransport
import com.google.api.client.testing.http.MockLowLevelHttpRequest
import com.google.api.client.testing.http.MockLowLevelHttpResponse
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SubscriptionListResponse
import org.junit.jupiter.api.Test

// TODO tbp: some details here: https://stackoverflow.com/questions/72291554/mock-bigquery-function-in-kotlin-unit-test
class SubscriptionsFetcherTest {

    @Test
    fun `fdsafsafsdfafds`() {
        val mockGoogleCredential = MockGoogleCredential.Builder().build()


        val mockResponse = MockLowLevelHttpResponse().setContent(
            """
              {
                "etag": "lEXfhWUBgEArRKM1qXBr2TK_lQQ",
                "items": [
                  {
                    "contentDetails": {
                      "activityType": "all",
                      "newItemCount": 0,
                      "totalItemCount": 5937
                    },
                    "etag": "IGyqiUOACK10Vbo2wHGCYC9vchc",
                    "id": "raQbgPETUOhIMDQmZCY4kTC-CbZd6cHC6r2rN12-YGg",
                    "kind": "youtube#subscription",
                    "snippet": {
                      "channelId": "UC5cw1VFE1rsZbN9Q2cA40aw",
                      "description": "Looking for a Tech YouTuber?\n\nLinus Tech Tips is a passionate team of \"professionally curious\" experts in consumer technology and video production which aims to inform and educate people of all ages through our entertaining videos. We create product reviews, step-by-step computer build guides, and a variety of other tech-focused content.\n\nSchedule:\nNew videos every Saturday to Thursday @ 10:00am Pacific\nLive WAN Show podcasts every Friday @ ~5:00pm Pacific",
                      "publishedAt": "2017-01-08T14:41:03.000Z",
                      "resourceId": {
                        "channelId": "UCXuqSBlHAE6Xw-yeJA0Tunw",
                        "kind": "youtube#channel"
                      },
                      "thumbnails": {
                        "default": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_qOpMK0tGNMCnReaXVp4ZvmNEIXn9eKeBGA_x-wQ=s88-c-k-c0x00ffffff-no-rj"
                        },
                        "high": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_qOpMK0tGNMCnReaXVp4ZvmNEIXn9eKeBGA_x-wQ=s800-c-k-c0x00ffffff-no-rj"
                        },
                        "medium": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_qOpMK0tGNMCnReaXVp4ZvmNEIXn9eKeBGA_x-wQ=s240-c-k-c0x00ffffff-no-rj"
                        }
                      },
                      "title": "Linus Tech Tips"
                    }
                  },
                  {
                    "contentDetails": {
                      "activityType": "all",
                      "newItemCount": 1,
                      "totalItemCount": 3780
                    },
                    "etag": "Z2ghtDlE80bi11vPAx1zFP2hMuU",
                    "id": "raQbgPETUOhIMDQmZCY4kcc2EZAKvvVKnIVcQ5NjOD4",
                    "kind": "youtube#subscription",
                    "snippet": {
                      "channelId": "UC5cw1VFE1rsZbN9Q2cA40aw",
                      "description": "New videos Monday-Saturday. Mostly StarCraft 2, but also other games from time to time. Thanks for watching!",
                      "publishedAt": "2019-08-17T06:18:32.866Z",
                      "resourceId": {
                        "channelId": "UCZNTsLA6t6bRoj-5QRmqt_w",
                        "kind": "youtube#channel"
                      },
                      "thumbnails": {
                        "default": {
                          "url": "https://yt3.ggpht.com/pdCTYYt8BOMjcLua1JzDSGzzkXMlXPlT2TC-PxLiT0QtO7UCI1DWsrHoV5mVdQJaTU1pQ6R6ECU=s88-c-k-c0x00ffffff-no-rj"
                        },
                        "high": {
                          "url": "https://yt3.ggpht.com/pdCTYYt8BOMjcLua1JzDSGzzkXMlXPlT2TC-PxLiT0QtO7UCI1DWsrHoV5mVdQJaTU1pQ6R6ECU=s800-c-k-c0x00ffffff-no-rj"
                        },
                        "medium": {
                          "url": "https://yt3.ggpht.com/pdCTYYt8BOMjcLua1JzDSGzzkXMlXPlT2TC-PxLiT0QtO7UCI1DWsrHoV5mVdQJaTU1pQ6R6ECU=s240-c-k-c0x00ffffff-no-rj"
                        }
                      },
                      "title": "LowkoTV"
                    }
                  },
                  {
                    "contentDetails": {
                      "activityType": "all",
                      "newItemCount": 0,
                      "totalItemCount": 488
                    },
                    "etag": "XexMzizwzgvtAELvz5Ha9Ev-4Ok",
                    "id": "raQbgPETUOiMW198mxPjkTZRIXN21HKXaatOj44kV54",
                    "kind": "youtube#subscription",
                    "snippet": {
                      "channelId": "UC5cw1VFE1rsZbN9Q2cA40aw",
                      "description": "What's in the box? Let's find out!\n\nOfficial channel under Linus Media Group.",
                      "publishedAt": "2020-02-15T11:46:15.829Z",
                      "resourceId": {
                        "channelId": "UCdBK94H6oZT2Q7l0-b0xmMg",
                        "kind": "youtube#channel"
                      },
                      "thumbnails": {
                        "default": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_-v2bzQCks3k_MHJNya5J_TSVpMevRw2-axtLX=s88-c-k-c0x00ffffff-no-rj"
                        },
                        "high": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_-v2bzQCks3k_MHJNya5J_TSVpMevRw2-axtLX=s800-c-k-c0x00ffffff-no-rj"
                        },
                        "medium": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_-v2bzQCks3k_MHJNya5J_TSVpMevRw2-axtLX=s240-c-k-c0x00ffffff-no-rj"
                        }
                      },
                      "title": "ShortCircuit"
                    }
                  },
                  {
                    "contentDetails": {
                      "activityType": "all",
                      "newItemCount": 0,
                      "totalItemCount": 1478
                    },
                    "etag": "yMaj3vORnU5ey3M8ojW5szb4kc8",
                    "id": "raQbgPETUOhIMDQmZCY4kdUSeIKSinjINE2FlbwwWiE",
                    "kind": "youtube#subscription",
                    "snippet": {
                      "channelId": "UC5cw1VFE1rsZbN9Q2cA40aw",
                      "description": "MKBHD: Quality Tech Videos | YouTuber | Geek | Consumer Electronics | Tech Head | Internet Personality!\n\nbusiness@MKBHD.com\n\nNYC",
                      "publishedAt": "2014-10-16T09:49:43.000Z",
                      "resourceId": {
                        "channelId": "UCBJycsmduvYEL83R_U4JriQ",
                        "kind": "youtube#channel"
                      },
                      "thumbnails": {
                        "default": {
                          "url": "https://yt3.ggpht.com/lkH37D712tiyphnu0Id0D5MwwQ7IRuwgQLVD05iMXlDWO-kDHut3uI4MgIEAQ9StK0qOST7fiA=s88-c-k-c0x00ffffff-no-rj"
                        },
                        "high": {
                          "url": "https://yt3.ggpht.com/lkH37D712tiyphnu0Id0D5MwwQ7IRuwgQLVD05iMXlDWO-kDHut3uI4MgIEAQ9StK0qOST7fiA=s800-c-k-c0x00ffffff-no-rj"
                        },
                        "medium": {
                          "url": "https://yt3.ggpht.com/lkH37D712tiyphnu0Id0D5MwwQ7IRuwgQLVD05iMXlDWO-kDHut3uI4MgIEAQ9StK0qOST7fiA=s240-c-k-c0x00ffffff-no-rj"
                        }
                      },
                      "title": "Marques Brownlee"
                    }
                  },
                  {
                    "contentDetails": {
                      "activityType": "all",
                      "newItemCount": 0,
                      "totalItemCount": 1113
                    },
                    "etag": "ZnDpt7MUt2ioi2UGIg4a_PQXfLU",
                    "id": "raQbgPETUOhIMDQmZCY4kXiGjD9NlLamGaJxfHL6f7w",
                    "kind": "youtube#subscription",
                    "snippet": {
                      "channelId": "UC5cw1VFE1rsZbN9Q2cA40aw",
                      "description": "Snazzy Labs has brought the most honest consumer tech reviews to the Net since 2008.\n\nWe do: Apple Mac Reviews | PC/Hackintosh builds | DIY Projects | Computer Mods | Smartphone Reviews | Tutorials | and More!\n\nBusiness contact email (companies only!): videos@snazzylabs.com",
                      "publishedAt": "2016-12-07T21:02:49.000Z",
                      "resourceId": {
                        "channelId": "UCO2x-p9gg9TLKneXlibGR7w",
                        "kind": "youtube#channel"
                      },
                      "thumbnails": {
                        "default": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_mlv5gj5YX0Zoc5VoadRftqG1-XU4H2Q70PA0HsQ=s88-c-k-c0x00ffffff-no-rj"
                        },
                        "high": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_mlv5gj5YX0Zoc5VoadRftqG1-XU4H2Q70PA0HsQ=s800-c-k-c0x00ffffff-no-rj"
                        },
                        "medium": {
                          "url": "https://yt3.ggpht.com/ytc/AMLnZu_mlv5gj5YX0Zoc5VoadRftqG1-XU4H2Q70PA0HsQ=s240-c-k-c0x00ffffff-no-rj"
                        }
                      },
                      "title": "Snazzy Labs"
                    }
                  }
                ],
                "kind": "youtube#SubscriptionListResponse",
                "nextPageToken": "CAUQAA",
                "pageInfo": {
                  "resultsPerPage": 5,
                  "totalResults": 330
                }
              }
        """.trimIndent()
        )
        val mockRequest = MockLowLevelHttpRequest()
        mockRequest.response = mockResponse

        val mockHttpTransport = MockHttpTransport.Builder()
//            .setLowLevelHttpRequest(mockRequest)
            .setLowLevelHttpResponse(mockResponse)
            .build()

        val youtube = YouTube.Builder(
            mockHttpTransport,
            mockGoogleCredential.jsonFactory,
            mockGoogleCredential
        ).build()

        val request = youtube
            .subscriptions()
            .list(listOf("snippet", "contentDetails"))
            .setMaxResults(5000)
            .setMine(true)
            .setFields("*")
            .setPrettyPrint(true)

        val responseWhichReturnsTheCorrectSubscriptionListPage: SubscriptionListResponse = request.execute()
        println(responseWhichReturnsTheCorrectSubscriptionListPage)

         /*
         todo pls help @google
         1. why can't i return the same mock response multiple times? the second iteration throws java.lang.IllegalArgumentException: no JSON input found
         2. how can i mock multiple responses? ie, if i were to consume nextPage, nextPage, multiple times
          */
        val responseWhichThrowsIAE: SubscriptionListResponse = request.setPageToken("CAUQAA").execute()
        println(responseWhichThrowsIAE)
    }
}
