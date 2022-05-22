pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
    maven { setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
  }
}

rootProject.name = "cashier-api"
include(":data-db", ":domain", ":utils")
include(":data-service")

include(":cashier-common-domain")
include(":cashier-common-data")