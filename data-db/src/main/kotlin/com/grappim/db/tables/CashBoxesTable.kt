package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object CashBoxesTable : UUIDTable(
  name = "cashboxes_table",
) {

  val name: Column<String> = varchar(
    name = "name",
    length = 50
  )

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

}