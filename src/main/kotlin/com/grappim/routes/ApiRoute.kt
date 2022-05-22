package com.grappim.routes

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.registerApiRoutes() {
  routing {
    apiRoute()
    jwks()
  }
}

private fun Routing.apiRoute() {
  route("/api/v1") {
    stockRouting()
    cashBoxRouting()
    authRouting()
    productRouting()
    productCategoryRouting()
    waybillRouting()
    waybillProductRouting()
    orderRouting()
    basketRouting()
    featureToggleRouting()
  }
}

private fun Routing.jwks() {
  static(".well-known") {
    staticRootFolder = File("certs")
    file("jwks.json")
  }
}