package com.grappim.domain.model.order

import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
  val id: Long,
  val merchantId: String,
  val stockId: String,
  val cashboxId: String,
  val timestamp: LocalDateTime,
  val totalSum: BigDecimal,
  val payType: PayType,
  val orderItems: List<OrderItem>
)
