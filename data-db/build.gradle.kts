plugins {
  kotlin("jvm")
}

dependencies {
  implementation(project(Modules.domain))
  implementation(project(Modules.utils))
  implementation(project(Modules.cashierCommonDomain))

  implementation(Deps.kotlinStdlib())

  implementation(Deps.exposedCore())
  implementation(Deps.exposedDao())
  implementation(Deps.exposedJdbc())
  implementation(Deps.exposedJavaTime())

  implementation(Deps.hikari)
  implementation(Deps.kodein)
}