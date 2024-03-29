/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.daos


import kotlin.collections.List

import land.tbp.jooq.tables.Oauth2token
import land.tbp.jooq.tables.records.Oauth2tokenRecord

import org.jooq.Configuration
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Oauth2tokenDao(configuration: Configuration?) : DAOImpl<Oauth2tokenRecord, land.tbp.jooq.tables.pojos.Oauth2token, Long>(Oauth2token.OAUTH2TOKEN, land.tbp.jooq.tables.pojos.Oauth2token::class.java, configuration) {

    /**
     * Create a new Oauth2tokenDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: land.tbp.jooq.tables.pojos.Oauth2token): Long? = o.oauth2tokenId

    /**
     * Fetch records that have <code>oauth2token_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfOauth2tokenId(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.OAUTH2TOKEN_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>oauth2token_id IN (values)</code>
     */
    fun fetchByOauth2tokenId(vararg values: Long): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.OAUTH2TOKEN_ID, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>oauth2token_id = value</code>
     */
    fun fetchOneByOauth2tokenId(value: Long): land.tbp.jooq.tables.pojos.Oauth2token? = fetchOne(Oauth2token.OAUTH2TOKEN.OAUTH2TOKEN_ID, value)

    /**
     * Fetch records that have <code>accessToken BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfAccesstoken(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.ACCESSTOKEN, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>accessToken IN (values)</code>
     */
    fun fetchByAccesstoken(vararg values: String): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.ACCESSTOKEN, *values)

    /**
     * Fetch records that have <code>tokenType BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfTokentype(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.TOKENTYPE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>tokenType IN (values)</code>
     */
    fun fetchByTokentype(vararg values: String): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.TOKENTYPE, *values)

    /**
     * Fetch records that have <code>expiresInSeconds BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfExpiresinseconds(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.EXPIRESINSECONDS, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>expiresInSeconds IN (values)</code>
     */
    fun fetchByExpiresinseconds(vararg values: Long): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.EXPIRESINSECONDS, *values.toTypedArray())

    /**
     * Fetch records that have <code>refreshToken BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfRefreshtoken(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.REFRESHTOKEN, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>refreshToken IN (values)</code>
     */
    fun fetchByRefreshtoken(vararg values: String): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.REFRESHTOKEN, *values)

    /**
     * Fetch records that have <code>scope BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfScope(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.SCOPE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>scope IN (values)</code>
     */
    fun fetchByScope(vararg values: String): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.SCOPE, *values)

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfUserId(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetchRange(Oauth2token.OAUTH2TOKEN.USER_ID, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    fun fetchByUserId(vararg values: Long): List<land.tbp.jooq.tables.pojos.Oauth2token> = fetch(Oauth2token.OAUTH2TOKEN.USER_ID, *values.toTypedArray())
}
