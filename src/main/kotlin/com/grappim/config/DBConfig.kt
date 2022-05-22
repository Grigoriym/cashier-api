package com.grappim.config

import com.grappim.db.di.DBConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Application.initDB() {
  val hikariDataSource by closestDI().instance<HikariDataSource>()

  Database.connect(hikariDataSource)
  DBConfig.createTables()
}
