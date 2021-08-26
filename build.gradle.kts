val javaVersion = JavaVersion.VERSION_16
val ktorVersion = "1.6.2"
val coroutinesVersion = "1.5.1"
val logbackVersion = "1.2.5"
val jacksonVersion = "2.12.4"
val junitVersion = "5.7.2"
val assertJVersion = "3.20.2"


group = "land.tbp"
version = "0.0.1"
description = "Send an email when a channel you follow on youtube has uploaded a video"
java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
    application
    java
    kotlin("jvm") version "1.5.30"
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



    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")

    implementation("com.google.api-client:google-api-client:1.32.1")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.32.1")
    implementation("com.google.apis:google-api-services-youtube:v3-rev20210811-1.32.1")

    //
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
            languageVersion = "1.5"
            apiVersion = "1.5"
        }
    }


    test {
        useJUnitPlatform()
    }
}
