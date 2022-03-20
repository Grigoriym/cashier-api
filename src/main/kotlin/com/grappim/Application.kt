package com.grappim

import com.grappim.config.configureAuth
import com.grappim.config.cors
import com.grappim.config.initDB
import com.grappim.config.statusPages
import com.grappim.di.initDI
import com.grappim.routes.registerApiRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
  initDI()
  initDB()

  install(DefaultHeaders)
  install(ContentNegotiation) {
    json(Json {
      isLenient = true
      prettyPrint = false
      ignoreUnknownKeys = true
      explicitNulls = true
    })
  }
  install(CallLogging) {
    level = Level.INFO
  }
  install(CORS) {
    cors()
  }
  configureAuth()
  install(StatusPages) {
    statusPages()
  }

  registerApiRoutes()
}

private fun getEnvironment(): String =
  System.getenv()["ENVIRONMENT"] ?: "dev"
