package land.tbp.land.tbp.db

import io.ktor.server.auth.*
import land.tbp.db.hikariDataSource
import land.tbp.jooq.tables.Oauth2token.Companion.OAUTH2TOKEN
import land.tbp.jooq.tables.daos.Oauth2tokenDao
import land.tbp.land.tbp.util.logger
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.time.Instant

val dslContext: DSLContext = DSL.using(hikariDataSource, SQLDialect.SQLITE)

class OAuth2TokenRepository {

    companion object {
        val log = logger()
    }

    private val oauth2Dao: Oauth2tokenDao by lazy { // todo should be constructor param
        Oauth2tokenDao(dslContext.configuration())
    }

    fun upsert(principal: OAuthAccessTokenResponse.OAuth2, userId: Long) {
        log.info("upsert $principal for user $userId")
        dslContext.transaction { trx ->
            trx.dsl().deleteFrom(OAUTH2TOKEN).where(OAUTH2TOKEN.USER_ID.eq(userId)).execute()

            val newRecord = trx.dsl().newRecord(OAUTH2TOKEN, principal).apply {
                expiresinseconds = principal.expiresIn
                scope = principal.extraParameters["scope"]
                this.userId = userId
                dbg()
                store()
            }
            oauth2Dao.findAll().dbg()
        }
    }

}

data class OAuth2Token(
    private val accessToken: String,
    private val tokenType: String,
    private val expiresInSeconds: Long,
    private val refreshToken: String,
    private val scope: String,
)


// TODO tbp: implement snowflake id because it sounds interesting
fun nextId(): Long = Instant.now().toEpochMilli()

fun <T : Any> T.dbg() = this.also { println(">${it::class}<\n$it") }
