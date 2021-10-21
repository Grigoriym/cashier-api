package com.grappim.config

import io.ktor.config.*

class JwtConfig(config: ApplicationConfig) {
    val privateKey = config.property("privateKey").getString()
    val issuer = config.property("issuer").getString()
    val audience = config.property("audience").getString()
    val realm = config.property("realm").getString()
    val kid = config.property("kid").getString()
}