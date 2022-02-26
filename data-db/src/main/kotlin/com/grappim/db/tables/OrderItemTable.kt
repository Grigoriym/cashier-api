package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.math.BigDecimal

object OrderItemTable : LongIdTable(
  name = "order_item_table"
) {

  val productId: Column<Long> = long(
    name = "productId"
  )

  val amount: Column<BigDecimal> = decimal(
    name = "amount",
    precision = 10,
    scale = 3
  )

  val sellingPrice: Column<BigDecimal> = decimal(
    name = "sellingPrice",
    precision = 10,
    scale = 3
  )
  val purchasePrice: Column<BigDecimal> = decimal(
    name = "purchasePrice",
    precision = 10,
    scale = 3
  )

  val barcode: Column<String> = varchar(
    name = "barcode",
    length = 50
  )

  val name: Column<String> = varchar(
    name = "name",
    length = 255
  )

  val order = reference(
    name = "order",
    foreign = OrderTable,
    onDelete = ReferenceOption.CASCADE
  )

}