/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.daos


import kotlin.collections.List

import land.tbp.jooq.tables.FlywaySchemaHistory
import land.tbp.jooq.tables.records.FlywaySchemaHistoryRecord

import org.jooq.Configuration
import org.jooq.impl.DAOImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class FlywaySchemaHistoryDao(configuration: Configuration?) : DAOImpl<FlywaySchemaHistoryRecord, land.tbp.jooq.tables.pojos.FlywaySchemaHistory, Long>(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, land.tbp.jooq.tables.pojos.FlywaySchemaHistory::class.java, configuration) {

    /**
     * Create a new FlywaySchemaHistoryDao without any configuration
     */
    constructor(): this(null)

    override fun getId(o: land.tbp.jooq.tables.pojos.FlywaySchemaHistory): Long? = o.installedRank

    /**
     * Fetch records that have <code>installed_rank BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfInstalledRank(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>installed_rank IN (values)</code>
     */
    fun fetchByInstalledRank(vararg values: Long): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK, *values.toTypedArray())

    /**
     * Fetch a unique record that has <code>installed_rank = value</code>
     */
    fun fetchOneByInstalledRank(value: Long): land.tbp.jooq.tables.pojos.FlywaySchemaHistory? = fetchOne(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK, value)

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfVersion(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.VERSION, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    fun fetchByVersion(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.VERSION, *values)

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfDescription(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.DESCRIPTION, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    fun fetchByDescription(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.DESCRIPTION, *values)

    /**
     * Fetch records that have <code>type BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfType(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.TYPE, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    fun fetchByType(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.TYPE, *values)

    /**
     * Fetch records that have <code>script BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfScript(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SCRIPT, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>script IN (values)</code>
     */
    fun fetchByScript(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SCRIPT, *values)

    /**
     * Fetch records that have <code>checksum BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfChecksum(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.CHECKSUM, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>checksum IN (values)</code>
     */
    fun fetchByChecksum(vararg values: Long): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.CHECKSUM, *values.toTypedArray())

    /**
     * Fetch records that have <code>installed_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfInstalledBy(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_BY, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>installed_by IN (values)</code>
     */
    fun fetchByInstalledBy(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_BY, *values)

    /**
     * Fetch records that have <code>installed_on BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfInstalledOn(lowerInclusive: String?, upperInclusive: String?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_ON, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>installed_on IN (values)</code>
     */
    fun fetchByInstalledOn(vararg values: String): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_ON, *values)

    /**
     * Fetch records that have <code>execution_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfExecutionTime(lowerInclusive: Long?, upperInclusive: Long?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.EXECUTION_TIME, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>execution_time IN (values)</code>
     */
    fun fetchByExecutionTime(vararg values: Long): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.EXECUTION_TIME, *values.toTypedArray())

    /**
     * Fetch records that have <code>success BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    fun fetchRangeOfSuccess(lowerInclusive: Boolean?, upperInclusive: Boolean?): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetchRange(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS, lowerInclusive, upperInclusive)

    /**
     * Fetch records that have <code>success IN (values)</code>
     */
    fun fetchBySuccess(vararg values: Boolean): List<land.tbp.jooq.tables.pojos.FlywaySchemaHistory> = fetch(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS, *values.toTypedArray())
}
