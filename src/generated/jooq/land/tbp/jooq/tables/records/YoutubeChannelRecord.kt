/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.records


import land.tbp.jooq.tables.YoutubeChannel

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record3
import org.jooq.Row3
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class YoutubeChannelRecord() : UpdatableRecordImpl<YoutubeChannelRecord>(YoutubeChannel.YOUTUBE_CHANNEL), Record3<Long?, String?, String?> {

    open var youtubeChannelId: Long?
        set(value): Unit = set(0, value)
        get(): Long? = get(0) as Long?

    open var channelId: String?
        set(value): Unit = set(1, value)
        get(): String? = get(1) as String?

    open var title: String?
        set(value): Unit = set(2, value)
        get(): String? = get(2) as String?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Long?> = super.key() as Record1<Long?>

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    override fun fieldsRow(): Row3<Long?, String?, String?> = super.fieldsRow() as Row3<Long?, String?, String?>
    override fun valuesRow(): Row3<Long?, String?, String?> = super.valuesRow() as Row3<Long?, String?, String?>
    override fun field1(): Field<Long?> = YoutubeChannel.YOUTUBE_CHANNEL.YOUTUBE_CHANNEL_ID
    override fun field2(): Field<String?> = YoutubeChannel.YOUTUBE_CHANNEL.CHANNEL_ID
    override fun field3(): Field<String?> = YoutubeChannel.YOUTUBE_CHANNEL.TITLE
    override fun component1(): Long? = youtubeChannelId
    override fun component2(): String? = channelId
    override fun component3(): String? = title
    override fun value1(): Long? = youtubeChannelId
    override fun value2(): String? = channelId
    override fun value3(): String? = title

    override fun value1(value: Long?): YoutubeChannelRecord {
        this.youtubeChannelId = value
        return this
    }

    override fun value2(value: String?): YoutubeChannelRecord {
        this.channelId = value
        return this
    }

    override fun value3(value: String?): YoutubeChannelRecord {
        this.title = value
        return this
    }

    override fun values(value1: Long?, value2: String?, value3: String?): YoutubeChannelRecord {
        this.value1(value1)
        this.value2(value2)
        this.value3(value3)
        return this
    }

    /**
     * Create a detached, initialised YoutubeChannelRecord
     */
    constructor(youtubeChannelId: Long? = null, channelId: String? = null, title: String? = null): this() {
        this.youtubeChannelId = youtubeChannelId
        this.channelId = channelId
        this.title = title
    }

    /**
     * Create a detached, initialised YoutubeChannelRecord
     */
    constructor(value: land.tbp.jooq.tables.pojos.YoutubeChannel?): this() {
        if (value != null) {
            this.youtubeChannelId = value.youtubeChannelId
            this.channelId = value.channelId
            this.title = value.title
        }
    }
}
