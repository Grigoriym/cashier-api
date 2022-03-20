package com.grappim.db.entities

import com.grappim.db.tables.BasketProductTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BasketProductEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<BasketProductEntity>(BasketProductTable)

  var barcode by BasketProductTable.barcode
  var name by BasketProductTable.name
  var stockId by BasketProductTable.stockId
  var amount by BasketProductTable.amount
  var unit by BasketProductTable.unit
  var merchantId by BasketProductTable.merchantId
  var sellingPrice by BasketProductTable.sellingPrice
  var basket by BasketEntity referencedOn BasketProductTable.basket
  var productId by BasketProductTable.productId
}