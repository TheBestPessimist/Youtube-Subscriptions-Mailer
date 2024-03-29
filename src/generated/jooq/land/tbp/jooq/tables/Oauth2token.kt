/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables


import java.util.function.Function

import kotlin.collections.List

import land.tbp.jooq.DefaultSchema
import land.tbp.jooq.indexes.OAUTH2TOKEN_OAUTH2TOKEN_ID_UINDEX
import land.tbp.jooq.indexes.OAUTH2TOKEN_USER_ID_UINDEX
import land.tbp.jooq.keys.OAUTH2TOKEN__FK_OAUTH2TOKEN_PK_USER
import land.tbp.jooq.keys.OAUTH2TOKEN__PK_OAUTH2TOKEN
import land.tbp.jooq.tables.records.Oauth2tokenRecord

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Index
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Row7
import org.jooq.Schema
import org.jooq.SelectField
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Oauth2token(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, Oauth2tokenRecord>?,
    aliased: Table<Oauth2tokenRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<Oauth2tokenRecord>(
    alias,
    DefaultSchema.DEFAULT_SCHEMA,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>oauth2token</code>
         */
        val OAUTH2TOKEN: Oauth2token = Oauth2token()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<Oauth2tokenRecord> = Oauth2tokenRecord::class.java

    /**
     * The column <code>oauth2token.oauth2token_id</code>.
     */
    val OAUTH2TOKEN_ID: TableField<Oauth2tokenRecord, Long?> = createField(DSL.name("oauth2token_id"), SQLDataType.BIGINT.nullable(false), this, "")

    /**
     * The column <code>oauth2token.accessToken</code>.
     */
    val ACCESSTOKEN: TableField<Oauth2tokenRecord, String?> = createField(DSL.name("accessToken"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>oauth2token.tokenType</code>.
     */
    val TOKENTYPE: TableField<Oauth2tokenRecord, String?> = createField(DSL.name("tokenType"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>oauth2token.expiresInSeconds</code>.
     */
    val EXPIRESINSECONDS: TableField<Oauth2tokenRecord, Long?> = createField(DSL.name("expiresInSeconds"), SQLDataType.BIGINT, this, "")

    /**
     * The column <code>oauth2token.refreshToken</code>.
     */
    val REFRESHTOKEN: TableField<Oauth2tokenRecord, String?> = createField(DSL.name("refreshToken"), SQLDataType.CLOB.nullable(false), this, "")

    /**
     * The column <code>oauth2token.scope</code>.
     */
    val SCOPE: TableField<Oauth2tokenRecord, String?> = createField(DSL.name("scope"), SQLDataType.CLOB, this, "")

    /**
     * The column <code>oauth2token.user_id</code>.
     */
    val USER_ID: TableField<Oauth2tokenRecord, Long?> = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<Oauth2tokenRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<Oauth2tokenRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>oauth2token</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>oauth2token</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>oauth2token</code> table reference
     */
    constructor(): this(DSL.name("oauth2token"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, Oauth2tokenRecord>): this(Internal.createPathAlias(child, key), child, key, OAUTH2TOKEN, null)
    override fun getSchema(): Schema? = if (aliased()) null else DefaultSchema.DEFAULT_SCHEMA
    override fun getIndexes(): List<Index> = listOf(OAUTH2TOKEN_OAUTH2TOKEN_ID_UINDEX, OAUTH2TOKEN_USER_ID_UINDEX)
    override fun getPrimaryKey(): UniqueKey<Oauth2tokenRecord> = OAUTH2TOKEN__PK_OAUTH2TOKEN
    override fun getReferences(): List<ForeignKey<Oauth2tokenRecord, *>> = listOf(OAUTH2TOKEN__FK_OAUTH2TOKEN_PK_USER)

    private lateinit var _user: User

    /**
     * Get the implicit join path to the <code>user</code> table.
     */
    fun user(): User {
        if (!this::_user.isInitialized)
            _user = User(this, OAUTH2TOKEN__FK_OAUTH2TOKEN_PK_USER)

        return _user;
    }

    val user: User
        get(): User = user()
    override fun `as`(alias: String): Oauth2token = Oauth2token(DSL.name(alias), this)
    override fun `as`(alias: Name): Oauth2token = Oauth2token(alias, this)
    override fun `as`(alias: Table<*>): Oauth2token = Oauth2token(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Oauth2token = Oauth2token(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Oauth2token = Oauth2token(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Oauth2token = Oauth2token(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row7<Long?, String?, String?, Long?, String?, String?, Long?> = super.fieldsRow() as Row7<Long?, String?, String?, Long?, String?, String?, Long?>

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, String?, String?, Long?, String?, String?, Long?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, String?, String?, Long?, String?, String?, Long?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
