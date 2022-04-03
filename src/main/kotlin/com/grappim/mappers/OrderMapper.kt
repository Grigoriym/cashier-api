package com.grappim.mappers

import com.grappim.common_data.model.order.CreateOrderDTO
import com.grappim.data_service.model.order.OrderItemDTO
import com.grappim.domain.model.order.CreateOrder
import com.grappim.domain.model.order.OrderItem

fun CreateOrderDTO.toDomain(): CreateOrder =
  CreateOrder(
    merchantId = this.merchantId,
    stockId = this.stockId,
    cashBoxId = this.cashBoxId,
    totalSum = this.totalSum,
    payType = this.payType,
    orderItems = this.orderItems.toDomain()
  )

fun List<OrderItemDTO>.toDomain(): List<OrderItem> =
  this.map {
    it.toDomain()
  }

fun OrderItemDTO.toDomain(): OrderItem = OrderItem(
  productId = this.productId,
  amount = this.amount,
  sellingPrice = this.sellingPrice,
  purchasePrice = this.purchasePrice,
  barcode = this.barcode,
  name = this.name
)