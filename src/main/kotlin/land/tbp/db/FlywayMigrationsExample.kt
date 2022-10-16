package land.tbp.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import kotlin.time.Duration.Companion.seconds

// TODO tbp: here's some more sqlite things i could try: https://cj.rs/blog/sqlite-pragma-cheatsheet-for-performance-and-consistency/
private val SQLITE_PRAGMAS = listOf(
    "PRAGMA foreign_keys=true",
    "PRAGMA reverse_unordered_selects=true",
    "PRAGMA journal_mode=wal",
    "PRAGMA integrity_check",
//    "PRAGMA query_only=true", // using this one to test if all pragmas are correct. for some retarded reason, sqlite silently swallows bad pragmas, so idk if all my pragmas are used. Dumb shit...
).joinToString(separator = ";", postfix = ";")

val hikariDataSource = HikariDataSource(HikariConfig().apply {
    driverClassName = "org.sqlite.JDBC"
    jdbcUrl = "jdbc:sqlite:zzzz.sqlite"
    connectionTestQuery = "SELECT 1"
    maxLifetime = 60.seconds.inWholeMilliseconds
    idleTimeout = 45.seconds.inWholeMilliseconds
    maximumPoolSize = 10
    connectionInitSql = SQLITE_PRAGMAS
})


fun main() {
    migrateDatabase()
}

fun migrateDatabase(): MigrateResult = Flyway.configure()
    /*
    initSql is needed because it is Flyway who creates the SQLite file, so some pragmas must be run at _db creation_ time, and not at _connection creation_ time
     */
    .initSql(SQLITE_PRAGMAS)
    .locations("db/migrations")
    .validateMigrationNaming(true)
    // todo: why do i want out of order?
    .outOfOrder(true)
    .sqlMigrationPrefix("")
    .dataSource(hikariDataSource)
    .load()
    .migrate()
