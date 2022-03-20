package com.grappim.data_service.service

import com.grappim.db.entities.OrderEntity
import com.grappim.db.entities.OrderItemEntity
import com.grappim.domain.model.order.CreateOrder
import com.grappim.domain.model.order.OrderItem
import com.grappim.domain.model.order.PayType
import com.grappim.domain.service.OrderService
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class OrderServiceImpl : OrderService, BaseService {

  override fun createOrder(
    createOrder: CreateOrder
  ): Long = transaction {
    val newOrder = OrderEntity.new {
      this.merchantId = createOrder.merchantId
      this.stockId = createOrder.stockId
      this.cashBoxId = createOrder.cashBoxId

      this.totalSum = createOrder.totalSum
      this.payType = PayType.getTypeFromString(createOrder.payType)

      this.timestamp = LocalDateTime.now()
    }

    val createdId = newOrder.id.value

    createOrderItems(createOrder.orderItems, newOrder)

    createdId
  }

  private fun createOrderItems(
    orderItems: List<OrderItem>,
    orderEntity: OrderEntity
  ) {
    orderItems.forEach { orderItem ->
      OrderItemEntity.new {
        this.productId = orderItem.productId
        this.amount = orderItem.amount
        this.sellingPrice = orderItem.sellingPrice
        this.purchasePrice = orderItem.purchasePrice
        this.barcode = orderItem.barcode
        this.name = orderItem.name
        this.order = orderEntity
      }
    }
  }

}