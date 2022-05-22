package com.grappim.config

import io.ktor.server.plugins.cors.*

fun CORSConfig.cors() {
  anyHost()
}