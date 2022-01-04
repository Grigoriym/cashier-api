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
  implementation(Deps.kotlinStdlib())
  implementation(Deps.kotlinSerialization())
}