package com.grappim.domain.model.order

import java.math.BigDecimal

data class CreateOrder(
  val merchantId: String,
  val stockId: String,
  val cashBoxId: String,
  val totalSum: BigDecimal,
  val payType: String,
  val orderItems: List<OrderItem>
)
