package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.*

object ProductCategoriesTable : LongIdTable(
  name = "product_category_table"
) {
  val name: Column<String> = varchar(
    name = "name",
    length = 100
  )
  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val createdOn = datetime(
    name = "createdOn"
  )

  val updatedOn = datetime(
    name = "updatedOn"
  )
}