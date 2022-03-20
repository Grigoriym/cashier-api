package com.grappim.domain.model.order

import java.math.BigDecimal
import java.util.UUID

data class CreateOrder(
  val merchantId: UUID,
  val stockId: UUID,
  val cashBoxId: UUID,
  val totalSum: BigDecimal,
  val payType: String,
  val orderItems: List<OrderItem>
)
