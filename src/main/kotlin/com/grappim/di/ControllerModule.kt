package com.grappim.di

import com.grappim.authentication.jwt.JwtController
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.controllerModule() {
    bind<JwtController>() with singleton {
        JwtController(
            spec = instance(),
            jwkProvider = instance()
        )
    }
}