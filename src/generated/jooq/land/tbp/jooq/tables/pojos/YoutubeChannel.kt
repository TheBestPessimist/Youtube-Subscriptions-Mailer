/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.pojos


import java.io.Serializable


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class YoutubeChannel(
    val youtubeChannelId: Long? = null,
    val channelId: String? = null,
    val title: String? = null
): Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other === null)
            return false
        if (this::class != other::class)
            return false
        val o: YoutubeChannel = other as YoutubeChannel
        if (this.youtubeChannelId === null) {
            if (o.youtubeChannelId !== null)
                return false
        }
        else if (this.youtubeChannelId != o.youtubeChannelId)
            return false
        if (this.channelId === null) {
            if (o.channelId !== null)
                return false
        }
        else if (this.channelId != o.channelId)
            return false
        if (this.title === null) {
            if (o.title !== null)
                return false
        }
        else if (this.title != o.title)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.youtubeChannelId === null) 0 else this.youtubeChannelId.hashCode())
        result = prime * result + (if (this.channelId === null) 0 else this.channelId.hashCode())
        result = prime * result + (if (this.title === null) 0 else this.title.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("YoutubeChannel (")

        sb.append(youtubeChannelId)
        sb.append(", ").append(channelId)
        sb.append(", ").append(title)

        sb.append(")")
        return sb.toString()
    }
}