package com.grappim.db.tables

import com.grappim.common_domain.model.ProductUnit
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.math.BigDecimal
import java.util.*

object BasketProductTable : LongIdTable(
  name = "basket_product_table"
) {

  val barcode: Column<String> = varchar(
    name = "barcode",
    length = 50
  ).uniqueIndex()

  val name: Column<String> = varchar(
    name = "name",
    length = 255
  ).uniqueIndex()

  val productId: Column<Long> = (long("productId")).references(
    ref = ProductsTable.id,
    onDelete = ReferenceOption.CASCADE
  ).uniqueIndex()

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
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

  val sellingPrice: Column<BigDecimal> = decimal(
    name = "sellingPrice",
    precision = 10,
    scale = 3
  )

  val basket = reference(
    name = "basket",
    foreign = BasketTable,
    onDelete = ReferenceOption.CASCADE
  )

}