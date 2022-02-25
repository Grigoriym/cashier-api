package com.grappim.db.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal

object WaybillProductTable : LongIdTable(
  name = "waybill_product_table"
) {

  val name: Column<String> = varchar(
    name = "name",
    length = 255
  )

  val barcode: Column<String> = varchar(
    name = "barcode",
    length = 255
  )

  val purchasePrice: Column<BigDecimal> = decimal(
    name = "purchasePrice",
    precision = 10,
    scale = 3
  )

  val sellingPrice: Column<BigDecimal> = decimal(
    name = "sellingPrice",
    precision = 10,
    scale = 3
  )

  val amount: Column<BigDecimal> = decimal(
    name = "amount",
    precision = 10,
    scale = 3
  )

  val waybillId: Column<Long> = (long("waybillId").references(
    ref = WaybillTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val productId: Column<Long> = (long("productId").references(
    ref = ProductsTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val createdOn = datetime(
    name = "createdOn"
  )

  val updatedOn = datetime(
    name = "updatedOn"
  )

}