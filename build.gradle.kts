import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  application
  kotlin("jvm") version Versions.kotlin
  kotlin("plugin.serialization") version Versions.kotlin
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
    jvmTarget = "13"
  }
}

dependencies {

  implementation(project(Modules.dataDb))
  implementation(project(Modules.domain))
  implementation(project(Modules.utils))

  implementation(Deps.kotlinStdlib())
  implementation(Deps.kotlinSerialization())

  implementation(Deps.ktorServerCore())
  implementation(Deps.ktorServerNetty())
  implementation(Deps.ktorSerialization())
  implementation(Deps.ktorAuth())
  implementation(Deps.ktorAuthJwt())

  implementation(Deps.exposedDao())

  implementation(Deps.logback)
  implementation(Deps.hikari)
  implementation(Deps.postgreSql)
  implementation(Deps.kodein)

  testImplementation(Deps.ktorServerTests())
  testImplementation(Deps.kotlinTest())
}