import org.jooq.meta.jaxb.ForcedType

val javaVersion = JavaVersion.VERSION_16
val kotlinLanguageVersion = "1.6"
val coroutinesVersion = "1.6.0"
val ktorVersion = "1.6.7"
val logbackVersion = "1.2.10"
val jacksonVersion = "2.13.1"
val junitVersion = "5.8.2"
val assertJVersion = "3.22.0"
val googleApiClientVersion = "1.33.2"
val googleOauthClientVersion = "1.33.1"
val googleYoutubeApiVersion = "v3-rev20210915-1.32.1"
val hopliteVersion = "1.4.16"
val sqliteJdbcVersion = "3.36.0.3"
val hikariCpVersion = "5.0.1"
val flywayVersion = "8.5.0"
val jooqVersion = "3.16.4"

group = "land.tbp"
version = "0.0.1"
description = "Send an email when a channel you follow on youtube has uploaded a video"
java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
    application
    java
    kotlin("jvm") version "1.6.20"
    id("nu.studer.jooq") version "7.1.1"

}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // ktor server
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")

    // ktor client
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")


    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")


    // google
    implementation("com.google.api-client:google-api-client:$googleApiClientVersion")
    implementation("com.google.oauth-client:google-oauth-client-jetty:$googleOauthClientVersion")
    implementation("com.google.apis:google-api-services-youtube:$googleYoutubeApiVersion")

    // database
    jooqGenerator("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    // TODO tbp: add jooq-kotlin dependency if it's useful

    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    implementation("com.zaxxer:HikariCP:$hikariCpVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")


    implementation("com.sksamuel.hoplite:hoplite-core:$hopliteVersion")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testImplementation("org.assertj:assertj-core:$assertJVersion")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    //    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
//    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")


    configurations.all {
        exclude(group = "junit", module = "junit")
        exclude(module = "mockito-core")
        exclude(module = "mockito-all")
        exclude(module = "slf4j-log4j12")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit")
    }
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            @Suppress("SuspiciousCollectionReassignment")
            freeCompilerArgs += listOf("-Xjsr305=strict")
            jvmTarget = javaVersion.majorVersion
            languageVersion = kotlinLanguageVersion
            apiVersion = kotlinLanguageVersion
        }
    }


    test {
        useJUnitPlatform()
    }

    wrapper {
        gradleVersion = "7.4.2"
    }
}

jooq {
    version.set(jooqVersion)

    configurations {
        create("main") {
            // omit task dependency from compileJava to generateJooq
            generateSchemaSourceOnCompilation.set(false)
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.sqlite.JDBC"
                    url = "jdbc:sqlite:zzzz.sqlite"
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.sqlite.SQLiteDatabase"
                        forcedTypes.add(ForcedType().apply {
                            /*
                            Transform Sqlite's INTEGER PRIMARY KEY AUTOINCREMENT columns to Long instead of Int in
                                Jooq generated code.
                            I name all my id columns as `[table_name]_id` (eg. select user.user_id from user),
                                so I'm using a sql to return all those columns.
                             */
                            name = "BIGINT"

                            //language=SQLite
                            sql = """
                                    WITH all_tables AS (SELECT name FROM sqlite_master WHERE type = 'table')
                                    SELECT pti.name
                                    FROM all_tables at INNER JOIN pragma_table_info(at.name) pti
                                        WHERE lower(pti.name) = lower(at.name || '_id')
                                """.trimIndent()
                        })
                    }
                    generate.apply {
                        isDeprecationOnUnknownTypes = true
                        isDaos = true
                        isJavaTimeTypes = true
                        isDeprecated = false
//                        isRecords = true
//                        isImmutablePojos = false
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "land.tbp.jooq"
                        directory = "src/generated/jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}
