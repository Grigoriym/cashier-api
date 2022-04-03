plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

dependencies {
  implementation(project(Modules.domain))
  implementation(project(Modules.utils))
  implementation(project(Modules.dataDb))

  implementation(project(Modules.cashierCommonDomain))
  implementation(project(Modules.cashierCommonData))

  implementation(Deps.kotlinStdlib())
  implementation(Deps.kotlinSerialization())

  implementation(Deps.exposedCore())
  implementation(Deps.exposedDao())

  implementation(Deps.ktorServerCore())
  implementation(Deps.ktorServerNetty())
  implementation(Deps.ktorSerialization())

  implementation(Deps.kodein)
}