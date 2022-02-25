package com.grappim.db.tables

import com.grappim.domain.model.general.ProductUnit
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.util.*

object ProductsTable : LongIdTable(
  name = "products_table"
) {

  val barcode: Column<String> = varchar(
    name = "barcode",
    length = 50
  ).uniqueIndex()

  val name: Column<String> = varchar(
    name = "name",
    length = 255
  ).uniqueIndex()

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val amount: Column<BigDecimal> = decimal(
    name = "amount",
    precision = 10,
    scale = 3
  )

  val unit: Column<ProductUnit> = enumeration(
    name = "unit",
    klass = ProductUnit::class
  )

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

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

  val createdOn = datetime(
    name = "createdOn"
  )

  val updatedOn = datetime(
    name = "updatedOn"
  )

  val categoryId: Column<Long> = (long(name = "categoryId").references(
    ref = ProductCategoriesTable.id,
    onDelete = ReferenceOption.SET_NULL
  ))
}