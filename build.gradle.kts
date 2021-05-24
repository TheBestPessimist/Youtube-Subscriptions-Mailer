@file:Suppress("PropertyName")

val kotlin_version = "1.5.0"
val ktor_version = kotlin_version
val coroutines_version = kotlin_version
val logback_version = "1.2.3"
val jacksonVersion = "2.12.3"
val junit5_version = "5.7.2"


group = "land.tbp"
version = "0.0.1"

plugins {
    application
    java
    kotlin("jvm") version "1.4.21"
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
//    implementation("io.ktor:ktor-client-http-timeout:$ktor_version") // TODO this library is not found anywhere for some reason
    implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("io.ktor:ktor-client-gson:$ktor_version")
    implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")

//
//    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jackson_version")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
//    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")





    testImplementation(platform("org.junit:junit-bom:$junit5_version"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("io.ktor:ktor-client-mock:$ktor_version")
    testImplementation("io.ktor:ktor-client-mock-jvm:$ktor_version")


}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_15.toString()
}
