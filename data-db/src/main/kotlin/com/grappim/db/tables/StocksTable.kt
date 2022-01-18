package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object StocksTable : UUIDTable(
  name = "stocks_table"
) {

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  )).uniqueIndex()

  val stockName: Column<String> = varchar(
    name = "stockName",
    length = 50
  )

}