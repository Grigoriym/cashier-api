package com.grappim.config

import com.auth0.jwk.JwkProvider
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.configureAuth() {
  val jwkConfig by closestDI().instance<JwtConfig>()
  val jwkProvider by closestDI().instance<JwkProvider>()

  install(Authentication) {
    jwt {
      realm = jwkConfig.realm
      verifier(jwkProvider, jwkConfig.issuer) {
        acceptLeeway(3)
      }
      validate { credential ->
        if (credential.payload.getClaim("id").asString().isNotEmpty()) {
          JWTPrincipal(credential.payload)
        } else {
          null
        }
      }
    }
  }
}