plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

val cashierSecretKey = extra["cashier_secret_key"] ?: "duct_tape_for_ci"

dependencies {
  implementation(Deps.kotlinStdlib())
  implementation(Deps.kotlinSerialization())

  implementation(project(Modules.domain))

  implementation(Deps.bcrypt)
}