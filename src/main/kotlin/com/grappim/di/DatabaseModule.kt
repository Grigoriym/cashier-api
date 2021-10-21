package com.grappim.di

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.kodein.di.*

fun DI.MainBuilder.databaseModule() {
    bindHikariConfig()
    bindHikariDataSource()
}

private fun DI.MainBuilder.bindHikariConfig() {
    bind<HikariConfig>() with singleton { HikariConfig(instance<String>(hikariConfigQualifier)) }
}

private fun DI.MainBuilder.bindHikariDataSource() {
    bind<HikariDataSource>() with singleton { HikariDataSource(instance()) }
}