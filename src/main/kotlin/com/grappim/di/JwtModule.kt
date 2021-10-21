package com.grappim.di

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import com.grappim.authentication.jwt.JwtSpec
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import java.security.PublicKey
import java.util.concurrent.TimeUnit

const val publicKey = "publicKey"

fun DI.MainBuilder.jwtModule() {
    bindJwkProvider()
}

private fun DI.MainBuilder.bindJwkProvider() {
    bind<JwkProvider>() with singleton {
        JwkProviderBuilder(instance<JwtSpec>().issuer)
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()
    }
}