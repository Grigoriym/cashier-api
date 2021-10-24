package com.grappim.db

import com.grappim.data.CashBoxes
import com.grappim.data.Stocks
import com.grappim.data.Users
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Sequence
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.slf4j.LoggerFactory

fun Application.initDB() {
    val hikariDataSource by closestDI().instance<HikariDataSource>()

    Database.connect(hikariDataSource)
    createTables()
    LoggerFactory.getLogger(
        Application::class.simpleName
    ).info("Initialized Database")
}

private fun createTables() = transaction {
//    SchemaUtils.drop(
//        Stocks,
//        Users,
//        CashBoxes
//    )//todo wait for versions migrations
    SchemaUtils.create(
        Users,
        Stocks,
        CashBoxes
    )
}