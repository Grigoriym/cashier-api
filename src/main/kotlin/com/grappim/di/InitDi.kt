package com.grappim.di

import com.grappim.data_service.di.serviceModule
import com.grappim.db.di.databaseModule
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