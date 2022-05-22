package com.grappim.di

import com.grappim.authentication.jwt.JwtSpec
import com.grappim.config.JwtConfig
import com.grappim.domain.password.PasswordManager
import com.grappim.utils.AppConfigs
import com.grappim.utils.password.PasswordManagerImpl
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.kodein.di.*

const val jwtQualifier = "jwt"

private const val dbconfig = "ktor.dbconfig"
fun DI.MainBuilder.applicationModule(application: Application) {
  bindApplicationConfig(application)
  bindJwtConfig()
  bindJwtSpec()

  bind<PasswordManager>() with singleton { PasswordManagerImpl() }
}

private fun DI.MainBuilder.bindApplicationConfig(application: Application) {
  bind<ApplicationConfig>(jwtQualifier) with provider { application.environment.config.config(jwtQualifier) }

  bind<String>(AppConfigs.hikariConfigQualifier) with provider {
    application.environment.config.property(dbconfig).getString()
  }
}

private fun DI.MainBuilder.bindJwtConfig() {
  bind<JwtConfig>() with singleton { JwtConfig(instance(jwtQualifier)) }
}

private fun DI.MainBuilder.bindJwtSpec() {
  bind<JwtSpec>() with singleton {
    instance<JwtConfig>().let {
      JwtSpec(
        privateKey = it.privateKey,
        issuer = it.issuer,
        audience = it.audience,
        realm = it.realm,
        kid = it.kid
      )
    }
  }
}