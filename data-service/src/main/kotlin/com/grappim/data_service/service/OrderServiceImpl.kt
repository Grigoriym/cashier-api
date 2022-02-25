package com.grappim.data_service.service

import com.grappim.db.entities.OrderEntity
import com.grappim.domain.model.order.CreateOrder
import com.grappim.domain.model.order.PayType
import com.grappim.domain.service.OrderService
import com.grappim.utils.toUUID
import java.time.LocalDateTime

class OrderServiceImpl : OrderService, BaseService {

  override fun createOrder(
    createOrder: CreateOrder
  ): Long {
    val newOrder = OrderEntity.new {
      this.merchantId = createOrder.merchantId.toUUID()
      this.stockId = createOrder.stockId.toUUID()
      this.cashBoxId = createOrder.cashBoxId.toUUID()

      this.totalSum = createOrder.totalSum
      this.payType = PayType.getTypeFromString(createOrder.payType)

      this.timestamp = LocalDateTime.now()
    }
    return newOrder.id.value
  }

}