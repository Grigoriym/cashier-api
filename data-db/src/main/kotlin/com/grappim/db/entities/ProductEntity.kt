package com.grappim.db.entities

import com.grappim.db.tables.ProductsTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductEntity(
  id: EntityID<Long>
) : LongEntity(id) {
  companion object : LongEntityClass<ProductEntity>(ProductsTable)

  var barcode by ProductsTable.barcode
  var name by ProductsTable.name
  var stockId by ProductsTable.stockId
  var amount by ProductsTable.amount
  var unit by ProductsTable.unit
  var merchantId by ProductsTable.merchantId
  var purchasePrice by ProductsTable.purchasePrice
  var sellingPrice by ProductsTable.sellingPrice
  var createdOn by ProductsTable.createdOn
  var updatedOn by ProductsTable.updatedOn
  var categoryId by ProductsTable.categoryId

}