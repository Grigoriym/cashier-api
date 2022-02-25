package com.grappim.db.di

import com.grappim.db.tables.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DBConfig {
  fun createTables() = transaction {
//    SchemaUtils.drop(
//      UsersTable,
//      StocksTable,
//      CashBoxesTable,
//      ProductsTable,
//      ProductCategoriesTable,
//      WaybillTable,
//      WaybillProductTable,
//      OrderTable,
//      OrderItemTable
//    )//todo wait for versions migrations

    SchemaUtils.create(
      UsersTable,
      StocksTable,
      CashBoxesTable,
      ProductsTable,
      ProductCategoriesTable,
      WaybillTable,
      WaybillProductTable,
      OrderTable,
      OrderItemTable
    )
  }
}
