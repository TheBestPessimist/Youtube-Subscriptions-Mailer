val javaVersion = JavaVersion.VERSION_16
val kotlinVersion = "1.5.0"
val ktorVersion = kotlinVersion
val coroutinesVersion = kotlinVersion
val logbackVersion = "1.2.3"
val jacksonVersion = "2.12.3"
val junitVersion = "5.7.2"
val assertJVersion = "3.19.0"


group = "land.tbp"
version = "0.0.1"
description = "Send an email when a channel you follow on youtube has uploaded a video"
java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
    application
    java
    kotlin("jvm") version "1.5.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
//    implementation("io.ktor:ktor-client-http-timeout:$ktor_version") // TODO this library is not found anywhere for some reason
    implementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")

//
//    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jackson_version")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
//    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")





    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktorVersion")
    testImplementation("org.assertj:assertj-core:$assertJVersion")


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
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion.majorVersion
            languageVersion = "1.5"
            apiVersion = "1.5"
        }
    }


    test {
        useJUnitPlatform()
        // Speedy tests.
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }
}
