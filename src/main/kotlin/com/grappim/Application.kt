package com.grappim

import com.grappim.config.configureAuth
import com.grappim.config.cors
import com.grappim.config.initDB
import com.grappim.config.statusPages
import com.grappim.di.initDI
import com.grappim.routes.registerApiRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.statuspages.*
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