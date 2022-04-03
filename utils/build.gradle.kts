import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

val cashierSecretKey = extra["cashier_secret_key"]

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
  implementation(Deps.kotlinStdlib())
  implementation(Deps.kotlinSerialization())

  implementation(project(Modules.domain))

  implementation(Deps.bcrypt)
}