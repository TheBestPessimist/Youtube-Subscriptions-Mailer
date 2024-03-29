/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.records


import javax.annotation.Nonnull

import land.tbp.jooq.tables.Subscription

import org.jooq.Field
import org.jooq.Record2
import org.jooq.Row2
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class SubscriptionRecord() : UpdatableRecordImpl<SubscriptionRecord>(Subscription.SUBSCRIPTION), Record2<Long?, Long?> {

    open var userId: Long?
        set(value): Unit = set(0, value)
    @Nonnull
        get(): Long? = get(0) as Long?

    open var youtubeChannelId: Long?
        set(value): Unit = set(1, value)
    @Nonnull
        get(): Long? = get(1) as Long?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record2<Long?, Long?> = super.key() as Record2<Long?, Long?>

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row2<Long?, Long?> = super.fieldsRow() as Row2<Long?, Long?>
    override fun valuesRow(): Row2<Long?, Long?> = super.valuesRow() as Row2<Long?, Long?>
    override fun field1(): Field<Long?> = Subscription.SUBSCRIPTION.USER_ID
    override fun field2(): Field<Long?> = Subscription.SUBSCRIPTION.YOUTUBE_CHANNEL_ID
    override fun component1(): Long? = userId
    override fun component2(): Long? = youtubeChannelId
    override fun value1(): Long? = userId
    override fun value2(): Long? = youtubeChannelId

    override fun value1(value: Long?): SubscriptionRecord {
        this.userId = value
        return this
    }

    override fun value2(value: Long?): SubscriptionRecord {
        this.youtubeChannelId = value
        return this
    }

    override fun values(value1: Long?, value2: Long?): SubscriptionRecord {
        this.value1(value1)
        this.value2(value2)
        return this
    }

    /**
     * Create a detached, initialised SubscriptionRecord
     */
    constructor(userId: Long? = null, youtubeChannelId: Long? = null): this() {
        this.userId = userId
        this.youtubeChannelId = youtubeChannelId
    }

    /**
     * Create a detached, initialised SubscriptionRecord
     */
    constructor(value: land.tbp.jooq.tables.pojos.Subscription?): this() {
        if (value != null) {
            this.userId = value.userId
            this.youtubeChannelId = value.youtubeChannelId
        }
    }
}
