import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "13"
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.utils))
    implementation(project(Modules.dataDb))

    implementation(Deps.kotlinStdlib())
    implementation(Deps.kotlinSerialization())

    implementation(Deps.exposedCore())
    implementation(Deps.exposedDao())

    implementation(Deps.ktorServerCore())
    implementation(Deps.ktorServerNetty())
    implementation(Deps.ktorSerialization())

    implementation(Deps.kodein)
}