package com.grappim.di

import io.ktor.application.*
import org.kodein.di.ktor.di

fun Application.initDI() {
    di {
        applicationModule(this@initDI)
        jwtModule()
        databaseModule()
        serviceModule()
        controllerModule()
    }
}