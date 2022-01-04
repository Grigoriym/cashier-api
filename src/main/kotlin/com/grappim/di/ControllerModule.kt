package com.grappim.di

import com.grappim.authentication.jwt.JwtController
import com.grappim.authentication.jwt.JwtControllerImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.controllerModule() {
  bind<JwtController>() with singleton {
    JwtControllerImpl(
      spec = instance(),
      jwkProvider = instance()
    )
  }
}