package com.grappim.mappers

import com.grappim.data_service.model.order.CreateOrderDTO
import com.grappim.domain.model.order.CreateOrder

fun CreateOrderDTO.toDomain(): CreateOrder =
  CreateOrder(
    merchantId = this.merchantId,
    stockId = this.stockId,
    cashBoxId = this.cashBoxId,
    totalSum = this.totalSum,
    payType = this.payType,
    orderItems = emptyList()
  )