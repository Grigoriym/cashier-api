object Deps {

  const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
  const val hikari = "com.zaxxer:HikariCP:${Versions.hikari}"
  const val postgreSql = "org.postgresql:postgresql:${Versions.postgreSql}"
  const val kodein = "org.kodein.di:kodein-di-framework-ktor-server-jvm:${Versions.kodein}"
  const val bcrypt = "at.favre.lib:bcrypt:${Versions.bcrypt}"

  private fun ktor(module: String = "", version: String = Versions.ktor) =
    "io.ktor:ktor$module:$version"

  private fun exposed(module: String = "", version: String = Versions.exposed) =
    "org.jetbrains.exposed:exposed$module:$version"

  private fun kotlin(module: String = "", version: String = Versions.kotlin) =
    "org.jetbrains.kotlin:kotlin$module:$version"

  private fun kotlinx(module: String = "", version: String = Versions.kotlin) =
    "org.jetbrains.kotlinx:kotlinx$module:$version"

  fun kotlinStdlib() = kotlin("-stdlib-jdk8")
  fun kotlinTest() = kotlin("-test")
  fun kotlinSerialization() = kotlinx(
    module = "-serialization-json",
    version = Versions.kotlinSerialization
  )

  fun ktorServerCore() = ktor("-server-core-jvm")
  fun ktorServerNetty() = ktor("-server-netty-jvm")
  fun ktorSerialization() = ktor("-serialization-kotlinx-json-jvm")
  fun ktorAuth() = ktor("-server-auth")
  fun ktorAuthJwt() = ktor("-server-auth-jwt")
  fun ktorServerTests() = ktor("-server-tests")

  fun ktorCors() = ktor("-server-cors")
  fun ktoStatusPages() = ktor("-server-status-pages")
  fun ktoDefaultHeaders() = ktor("-server-default-headers")
  fun ktorCallLogging() = ktor("-server-call-logging")
  fun ktorContentNegotiation() = ktor("-server-content-negotiation")

  fun exposedCore() = exposed("-core")
  fun exposedDao() = exposed("-dao")
  fun exposedJdbc() = exposed("-jdbc")
  fun exposedJavaTime() = exposed("-java-time")
}