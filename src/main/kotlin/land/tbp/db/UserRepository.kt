package land.tbp.land.tbp.db

import land.tbp.jooq.tables.daos.UserDao
import land.tbp.jooq.tables.records.UserRecord
import land.tbp.jooq.tables.references.USER
import land.tbp.land.tbp.util.logger
import land.tbp.land.tbp.youtube.GoogleUserInfo

class UserRepository {
    private val userDao: UserDao by lazy { // todo should be constructor param
        UserDao(dslContext.configuration())
    }

    companion object {
        val log = logger()
    }

    private val u = USER

    fun upsert(googleUserInfo: GoogleUserInfo): UserRecord {
        val userRecord = dslContext.fetchOne(u, u.GOOGLE_USER_ID.eq(googleUserInfo.googleUserId))

      return  if (userRecord == null) persistUser(googleUserInfo)
        else updateUser(googleUserInfo, userRecord)
        // TODO tbp: do i need to update here? i mean when a user account is created, what can be changed? todo: decide!
        //      anyway, this is the code to check if stuff changed. decide later if that's needed or not
        //        val a = user!!.into(GoogleUserInfo::class.java)
        //        a == googleUserInfo
    }

    private fun persistUser(googleUserInfo: GoogleUserInfo): UserRecord {
        val userRecord = dslContext.newRecord(u, googleUserInfo)
        userRecord.dbg()
        userRecord.store().dbg()
        userDao.findAll().dbg()
        return userRecord
    }

    private fun updateUser(googleUserInfo: GoogleUserInfo, userRecord: UserRecord): UserRecord {
        userRecord.dbg()
        userRecord.googleUserId = googleUserInfo.googleUserId
        userRecord.name = googleUserInfo.name
        userRecord.email = googleUserInfo.email
        userRecord.dbg()
        userRecord.store().dbg()
        return userRecord
    }
}
