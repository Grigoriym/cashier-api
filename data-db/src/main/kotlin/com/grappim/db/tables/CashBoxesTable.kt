package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

private const val cashBoxesTableName = "cashboxes_table"

object CashBoxesTable : UUIDTable(
  name = cashBoxesTableName,
) {

  val name: Column<String> = varchar(
    name = "name",
    length = 50
  )

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  )).uniqueIndex()

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  )).uniqueIndex()

}