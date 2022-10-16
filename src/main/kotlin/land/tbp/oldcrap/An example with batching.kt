package land.tbp.land.tbp.oldcrap

/*

fun fdsa(): Unit {
    GoogleAuthRepo.authenticatedUsers.forEach { tokenResponse ->
        val credential = createCredentialWithRefreshToken(tokenResponse)
        val youtube: YouTube = youtubeService.createForUser(credential)

        val listRequest: HttpRequest = youtube.subscriptions()
            .list(listOf("snippet", "contentDetails"))
            .setPrettyPrint(true)
            .setMine(true)
            .setFields("*")
            .buildHttpRequest()

        youtube.batch()
            .queue(listRequest, SubscriptionListResponse::class.java, GoogleJsonErrorContainer::class.java, object : JsonBatchCallback<SubscriptionListResponse>() {
                override fun onSuccess(s: SubscriptionListResponse, responseHeaders: HttpHeaders?) {
                    println(s)
                    println(s.pageInfo.totalResults)
                    println()
                    println()
                }

                override fun onFailure(e: GoogleJsonError, responseHeaders: HttpHeaders) {
                    error(e) // todo no idea what to do in case of error `¯\_(ツ)_/¯`
                }
            })
            .execute()

    }
}
*/
