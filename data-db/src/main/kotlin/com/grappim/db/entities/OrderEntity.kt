package com.grappim.db.entities

import com.grappim.db.tables.OrderItemTable
import com.grappim.db.tables.OrderTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class OrderEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<OrderEntity>(OrderTable)

  var merchantId by OrderTable.merchantId
  var stockId by OrderTable.stockId
  var cashBoxId by OrderTable.cashBoxId
  var timestamp by OrderTable.timestamp
  var totalSum by OrderTable.totalSum
  var payType by OrderTable.payType

  val orderItems by OrderItemEntity referrersOn OrderItemTable.order
}