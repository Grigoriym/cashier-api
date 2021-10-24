package com.grappim.di

import com.grappim.authentication.jwt.JwtSpec
import com.grappim.config.JwtConfig
import io.ktor.application.*
import io.ktor.config.*
import org.kodein.di.*

const val jwtQualifier = "jwt"

const val hikariConfigQualifier = "qualifier.hikari-config"
private const val dbconfig = "ktor.dbconfig"

fun DI.MainBuilder.applicationModule(application: Application) {
    bindApplicationConfig(application)
    bindJwtConfig()
    bindJwtSpec()
}

private fun DI.MainBuilder.bindApplicationConfig(application: Application) {
    bind<ApplicationConfig>(jwtQualifier) with provider { application.environment.config.config(jwtQualifier) }

    bind<String>(hikariConfigQualifier) with provider { application.environment.config.property(dbconfig).getString() }
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