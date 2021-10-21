import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor: String by project
val kotlin: String by project
val logback: String by project
val exposed: String by project
val kodein: String by project
val hikari: String by project
val postgreSql: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "com.grappim"
version = "0.0.1"

application {
    mainClass.set("com.grappim.ApplicationKt")
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    fun ktor(module: String = "", version: String = ktor) = "io.ktor:ktor$module:$version"
    fun exposed(module: String = "", version: String = exposed) = "org.jetbrains.exposed:exposed$module:$version"

    implementation(ktor("-server-core"))
    implementation(ktor("-server-netty"))
    implementation(ktor("-serialization"))
    implementation(ktor("-auth"))
    implementation(ktor("-auth-jwt"))

    implementation(exposed("-core"))
    implementation(exposed("-dao"))
    implementation(exposed("-jdbc"))

    implementation("ch.qos.logback:logback-classic:$logback")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodein")
    implementation("org.postgresql:postgresql:$postgreSql")
    implementation("com.zaxxer:HikariCP:$hikari")

    testImplementation(ktor("-server-tests"))
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin")
}