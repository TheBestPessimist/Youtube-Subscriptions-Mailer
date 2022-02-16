package land.tbp.land.tbp

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult


val hikariDataSource = HikariDataSource(HikariConfig().apply {
    driverClassName = "org.sqlite.JDBC"
    jdbcUrl = "jdbc:sqlite:zzzz.sqlite"
    connectionTestQuery = "SELECT 1"
    maxLifetime = 60000 // 60 Sec
    idleTimeout = 45000 // 45 Sec
    maximumPoolSize = 10
})


fun main() {
    migrateDatabase()


}

fun migrateDatabase(): MigrateResult = Flyway.configure()
    .locations("db/migrations")
    .validateMigrationNaming(true)
    .outOfOrder(true)
    .sqlMigrationPrefix("")
    .dataSource(hikariDataSource)
    .load()
    .migrate()
