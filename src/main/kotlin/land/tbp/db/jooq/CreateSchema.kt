package land.tbp.land.tbp.db.jooq

import land.tbp.land.tbp.db.dslContext
import org.jooq.impl.DSL.constraint
import org.jooq.impl.SQLDataType

fun main() {
    dslContext.createTable("user")
        .column("user_id", SQLDataType.BIGINTUNSIGNED)
//        .column("email", )
            .constraint(constraint("user_pk").primaryKey("user_id"))

        .execute()
}
