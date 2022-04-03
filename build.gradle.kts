import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  application
  kotlin("jvm") version Versions.kotlin
  kotlin("plugin.serialization") version Versions.kotlin
  id(Plugins.gradleVersions) version Versions.gradleVersions
}

group = "com.grappim"
version = "0.0.1"

application {
  mainClass.set("com.grappim.ApplicationKt")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "13"
  }
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

subprojects {
  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "13"
    }
  }
}

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    isNonStable(candidate.version) && !isNonStable(currentVersion)
  }

  checkForGradleUpdate = true
  outputFormatter = "json"
  outputDir = "build/dependencyUpdates"
  reportfileName = "report"
}

dependencies {
  implementation(project(Modules.dataDb))
  implementation(project(Modules.dataService))
  implementation(project(Modules.domain))
  implementation(project(Modules.utils))

  implementation(project(Modules.cashierCommonDomain))
  implementation(project(Modules.cashierCommonData))

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