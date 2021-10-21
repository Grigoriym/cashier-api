package com.grappim.config

import io.ktor.features.*

fun CORS.Configuration.cors() {
    anyHost()
}