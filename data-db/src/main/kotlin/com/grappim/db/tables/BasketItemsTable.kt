package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object BasketItemsTable : LongIdTable(
  name = "basket_items_table"
) {

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val cashBoxId: Column<UUID> = (uuid(name = "cashBoxId").references(
    ref = CashBoxesTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

}