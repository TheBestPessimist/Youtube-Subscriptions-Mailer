package land.tbp.land.tbp.config

data class Config(
    val googleCredentials: GoogleCredentials
)

data class GoogleCredentials(
    val clientId: String,
    val clientSecret: String,
)
