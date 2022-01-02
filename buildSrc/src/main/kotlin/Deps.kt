object Deps {

  const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
  const val hikari = "com.zaxxer:HikariCP:${Versions.hikari}"
  const val postgreSql = "org.postgresql:postgresql:${Versions.postgreSql}"
  const val kodein = "org.kodein.di:kodein-di-framework-ktor-server-jvm:${Versions.kodein}"

  private fun ktor(module: String = "", version: String = Versions.ktor) =
    "io.ktor:ktor$module:$version"

  private fun exposed(module: String = "", version: String = Versions.exposed) =
    "org.jetbrains.exposed:exposed$module:$version"

  private fun kotlin(module: String = "", version: String = Versions.kotlin) =
    "org.jetbrains.kotlin:kotlin$module:$version"

  fun kotlinTest() = kotlin("-test")

  fun ktorServerCore() = ktor("-server-core")
  fun ktorServerNetty() = ktor("-server-netty")
  fun ktorSerialization() = ktor("-serialization")
  fun ktorAuth() = ktor("-auth")
  fun ktorAuthJwt() = ktor("-auth-jwt")
  fun ktorServerTests() = ktor("-server-tests")

  fun exposedCore() = exposed("-core")
  fun exposedDao() = exposed("-dao")
  fun exposedJdbc() = exposed("-jdbc")
  fun exposedJavaTime() = exposed("-java-time")
}