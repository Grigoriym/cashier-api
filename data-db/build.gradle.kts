import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
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

  implementation(Deps.kotlinStdlib())

  implementation(Deps.exposedCore())
  implementation(Deps.exposedDao())
  implementation(Deps.exposedJdbc())
  implementation(Deps.exposedJavaTime())

  implementation(Deps.hikari)
  implementation(Deps.kodein)
}