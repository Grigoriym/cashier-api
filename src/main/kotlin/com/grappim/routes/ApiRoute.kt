package com.grappim.routes

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
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
    }
}

private fun Routing.jwks() {
    static(".well-known") {
        staticRootFolder = File("certs")
        file("jwks.json")
    }
}