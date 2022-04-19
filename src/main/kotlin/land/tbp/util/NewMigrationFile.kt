package land.tbp.land.tbp.util

import java.nio.file.Files
import java.nio.file.Path
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun main() = createMigrationFile()

fun createMigrationFile() {
    val migrationsFolder = Path.of("", "src", "main", "resources", "db", "migrations").toAbsolutePath()

    val nowFormatted = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"))

    val fileName = "${nowFormatted}__migration.sql"
    val filePath = migrationsFolder.resolve(fileName)


    println("""
            Will create the file 
            "$filePath"
            
            Please update update the name of the file and the content.
        """.trimIndent())

    Files.writeString(filePath, "")
}
