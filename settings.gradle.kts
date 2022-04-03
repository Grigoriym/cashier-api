pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

rootProject.name = "cashier-api"
include(":data-db", ":domain", ":utils")
include(":data-service")

include(":cashier-common-domain")
include(":cashier-common-data")