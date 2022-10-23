package land.tbp.land.tbp.config

import land.tbp.land.tbp.db.UserRepository
import land.tbp.land.tbp.db.dslContext

object Components {
    val userRepository by lazy { UserRepository(dslContext.configuration()) }
}
