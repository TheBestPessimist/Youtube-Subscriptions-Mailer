/*
 * This file is generated by jOOQ.
 */
package land.tbp.jooq.tables.pojos


import java.io.Serializable


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
data class Oauth2token(
    var oauth2tokenId: Long? = null,
    var accesstoken: String? = null,
    var tokentype: String? = null,
    var expiresinseconds: Long? = null,
    var refreshtoken: String? = null,
    var scope: String? = null,
    var userId: Long? = null
): Serializable {


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other === null)
            return false
        if (this::class != other::class)
            return false
        val o: Oauth2token = other as Oauth2token
        if (this.oauth2tokenId === null) {
            if (o.oauth2tokenId !== null)
                return false
        }
        else if (this.oauth2tokenId != o.oauth2tokenId)
            return false
        if (this.accesstoken === null) {
            if (o.accesstoken !== null)
                return false
        }
        else if (this.accesstoken != o.accesstoken)
            return false
        if (this.tokentype === null) {
            if (o.tokentype !== null)
                return false
        }
        else if (this.tokentype != o.tokentype)
            return false
        if (this.expiresinseconds === null) {
            if (o.expiresinseconds !== null)
                return false
        }
        else if (this.expiresinseconds != o.expiresinseconds)
            return false
        if (this.refreshtoken === null) {
            if (o.refreshtoken !== null)
                return false
        }
        else if (this.refreshtoken != o.refreshtoken)
            return false
        if (this.scope === null) {
            if (o.scope !== null)
                return false
        }
        else if (this.scope != o.scope)
            return false
        if (this.userId === null) {
            if (o.userId !== null)
                return false
        }
        else if (this.userId != o.userId)
            return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if (this.oauth2tokenId === null) 0 else this.oauth2tokenId.hashCode())
        result = prime * result + (if (this.accesstoken === null) 0 else this.accesstoken.hashCode())
        result = prime * result + (if (this.tokentype === null) 0 else this.tokentype.hashCode())
        result = prime * result + (if (this.expiresinseconds === null) 0 else this.expiresinseconds.hashCode())
        result = prime * result + (if (this.refreshtoken === null) 0 else this.refreshtoken.hashCode())
        result = prime * result + (if (this.scope === null) 0 else this.scope.hashCode())
        result = prime * result + (if (this.userId === null) 0 else this.userId.hashCode())
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder("Oauth2token (")

        sb.append(oauth2tokenId)
        sb.append(", ").append(accesstoken)
        sb.append(", ").append(tokentype)
        sb.append(", ").append(expiresinseconds)
        sb.append(", ").append(refreshtoken)
        sb.append(", ").append(scope)
        sb.append(", ").append(userId)

        sb.append(")")
        return sb.toString()
    }
}
