package com.grappim.config

import com.auth0.jwk.JwkProvider
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.configureAuth() {
    val jwkConfig by closestDI().instance<JwtConfig>()
    val jwkProvider by closestDI().instance<JwkProvider>()

    install(Authentication) {
        jwt() {
            realm = jwkConfig.realm
            verifier(jwkProvider, jwkConfig.issuer) {
                acceptLeeway(3)
            }
            validate { credential ->
                if (credential.payload.getClaim("email").asString().isNotBlank()) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}