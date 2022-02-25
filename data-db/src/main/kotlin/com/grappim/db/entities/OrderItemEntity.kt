package com.grappim.db.entities

import com.grappim.db.tables.OrderItemTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OrderItemEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<OrderItemEntity>(OrderItemTable)

  var productId by OrderItemTable.productId
  var amount by OrderItemTable.amount
  var sellingPrice by OrderItemTable.sellingPrice
  var purchasePrice by OrderItemTable.purchasePrice
  var barcode by OrderItemTable.barcode
  var name by OrderItemTable.name

}