package land.tbp.config

import land.tbp.land.tbp.db.dslContext
import land.tbp.land.tbp.db.migrateDatabase
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
interface BaseTest {
    @BeforeAll
    fun baseBeforeAll() {
        migrateDatabase()
    }

    @BeforeEach
    fun baseBeforeEach() {
        dslContext.meta().tables.forEach { dslContext.truncate(it).execute() }
    }
}
