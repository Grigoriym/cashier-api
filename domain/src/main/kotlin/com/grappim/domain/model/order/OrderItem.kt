package com.grappim.domain.model.order

import java.math.BigDecimal

data class OrderItem(
  val id: Long,
  val productId: Long,
  val amount: BigDecimal,
  val sellingPrice: BigDecimal,
  val purchasePrice: BigDecimal,
  val barcode: String,
  val name: String
)
