/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables


import java.util.function.Function

import kotlin.collections.List

import land.tbp.jooq.DefaultSchema
import land.tbp.jooq.indexes.USER_GOOGLE_USER_ID_UINDEX
import land.tbp.jooq.keys.USER__PK_USER
import land.tbp.jooq.tables.records.UserRecord

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Index
import org.jooq.Name
import org.jooq.Record
import org.jooq.Records
import org.jooq.Row4
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
open class User(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, UserRecord>?,
    aliased: Table<UserRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<UserRecord>(
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
         * The reference instance of <code>user</code>
         */
        val USER: User = User()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<UserRecord> = UserRecord::class.java

    /**
     * The column <code>user.user_id</code>.
     */
    val USER_ID: TableField<UserRecord, Long?> = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>user.email</code>.
     */
    val EMAIL: TableField<UserRecord, String?> = createField(DSL.name("email"), SQLDataType.CLOB.nullable(false), this, "")

    /**
     * The column <code>user.name</code>.
     */
    val NAME: TableField<UserRecord, String?> = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "")

    /**
     * The column <code>user.google_user_id</code>.
     */
    val GOOGLE_USER_ID: TableField<UserRecord, String?> = createField(DSL.name("google_user_id"), SQLDataType.CLOB.nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<UserRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<UserRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>user</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>user</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>user</code> table reference
     */
    constructor(): this(DSL.name("user"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, UserRecord>): this(Internal.createPathAlias(child, key), child, key, USER, null)
    override fun getSchema(): Schema? = if (aliased()) null else DefaultSchema.DEFAULT_SCHEMA
    override fun getIndexes(): List<Index> = listOf(USER_GOOGLE_USER_ID_UINDEX)
    override fun getIdentity(): Identity<UserRecord, Long?> = super.getIdentity() as Identity<UserRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<UserRecord> = USER__PK_USER
    override fun `as`(alias: String): User = User(DSL.name(alias), this)
    override fun `as`(alias: Name): User = User(alias, this)
    override fun `as`(alias: Table<*>): User = User(alias.getQualifiedName(), this)

    /**
     * Rename this table
     */
    override fun rename(name: String): User = User(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): User = User(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): User = User(name.getQualifiedName(), null)

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row4<Long?, String?, String?, String?> = super.fieldsRow() as Row4<Long?, String?, String?, String?>

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    fun <U> mapping(from: (Long?, String?, String?, String?) -> U): SelectField<U> = convertFrom(Records.mapping(from))

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    fun <U> mapping(toType: Class<U>, from: (Long?, String?, String?, String?) -> U): SelectField<U> = convertFrom(toType, Records.mapping(from))
}
