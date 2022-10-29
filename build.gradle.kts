import nu.studer.gradle.jooq.JooqEdition.*
import org.jooq.meta.jaxb.ForcedType

val javaVersion = JavaVersion.VERSION_18
val kotlinLanguageVersion = "1.7"
val gradle = "7.5.1"
val kotlin = "1.7.20"
val coroutines = "1.6.4"
val ktor = "2.1.2"

@Suppress("PropertyName")
val ktor_version = ktor
val logback = "1.4.3"
val jackson = "2.13.4"
val junit = "5.9.0"
val assertJ = "3.23.1"
val googleApiClient = "1.33.2"
val googleOauthClient = "1.33.1"
val googleYoutubeApi = "v3-rev20220926-2.0.0"
val hoplite = "2.6.4"
val sqliteJdbc = "3.39.3.0"
val hikariCp = "5.0.1"
val flyway = "9.4.0"
val jooq = "3.17.4"

group = "land.tbp"
version = "0.0.1"
description = "Send an email when a channel you follow on youtube has uploaded a video"
java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
    application
    java
    kotlin("jvm") version "1.7.20"
    id("nu.studer.jooq") version "8.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("ch.qos.logback:logback-classic:$logback")

    // ktor server
    implementation("io.ktor:ktor-server-core:$ktor")
    implementation("io.ktor:ktor-server-netty:$ktor")

    implementation("io.ktor:ktor-server-content-negotiation:$ktor")
    implementation("io.ktor:ktor-serialization-jackson:$ktor")
    implementation("io.ktor:ktor-server-auth:$ktor")
    implementation("io.ktor:ktor-server-call-logging:$ktor")
    implementation("io.ktor:ktor-server-auto-head-response:$ktor")
    implementation("io.ktor:ktor-server-default-headers:$ktor")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")

    // ktor client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor")
    implementation("io.ktor:ktor-client-logging-jvm:$ktor")
    implementation("io.ktor:ktor-client-auth:$ktor_version")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jackson")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jackson")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jackson")


    // google
//    implementation("com.google.api-client:google-api-client:$googleApiClient")
//    implementation("com.google.oauth-client:google-oauth-client-jetty:$googleOauthClient")
    implementation("com.google.apis:google-api-services-youtube:$googleYoutubeApi")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.11.0")

    // database
    jooqGenerator("org.xerial:sqlite-jdbc:$sqliteJdbc")
    implementation("org.jooq:jooq-kotlin") {
        version { strictly(jooq) }
        because("The jooq gradle plugin adds its own obsolete jooq version like a douche.")
    }

    implementation("org.xerial:sqlite-jdbc:$sqliteJdbc")
    implementation("com.zaxxer:HikariCP:$hikariCp")
    implementation("org.flywaydb:flyway-core:$flyway")


    implementation("com.sksamuel.hoplite:hoplite-core:$hoplite")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter:$junit")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit")

    testImplementation("org.assertj:assertj-core:$assertJ")

    testImplementation("io.ktor:ktor-server-test-host:$ktor")
    //    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
//    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
//    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")


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
        gradleVersion = gradle
    }
}

jooq {
    version.set(jooq)

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
                        forcedTypes.add(ForcedType().apply {
                            // name is the java type that i want, according to the mapping in `org.jooq.impl.SQLDataType`
                            name = "BIGINT"
                            // includeTypes is a regex capturing all the different types from the database which should be forced into my desired type above. Afais it's case insensitive by default
                            includeTypes = "int|integer"
                        })

                    }
                    generate.apply {
                        isDeprecationOnUnknownTypes = true
                        isDaos = true
                        isPojos = true
                        isJavaTimeTypes = true
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                        isGeneratedAnnotation = false
                        isNullableAnnotation = true
                        isNonnullAnnotation = true
                        isConstructorPropertiesAnnotation = true
                        isConstructorPropertiesAnnotationOnPojos = true
                        isConstructorPropertiesAnnotationOnRecords = true
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

 // TODO tbp: implement migrations with flyway, coz why not? https://github.com/etiennestuder/gradle-jooq-plugin/blob/master/example/configure_jooq_with_flyway/build.gradle
tasks.withType<nu.studer.gradle.jooq.JooqGenerate> { allInputsDeclared.set(true) }
