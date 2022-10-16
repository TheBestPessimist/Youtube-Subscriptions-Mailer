package land.tbp.land.tbp.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.parsers.PropsParser

val config: Config by lazy {
    ConfigLoader.builder()
        .addFileExtensionMapping("env", PropsParser())
        .build()
        .loadConfigOrThrow(
            """.env.properties"""
        )
}

data class Config(
    val googleCredentials: GoogleCredentials,
)

data class GoogleCredentials(
    val clientId: String,
    val clientSecret: String,
)
