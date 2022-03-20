package com.grappim.domain.model.product

import java.math.BigDecimal
import java.util.UUID

data class CreateProduct(
  val name: String,
  val stockId: UUID,
  val merchantId: UUID,
  val unit: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val amount: BigDecimal,
  val barcode: String,
  val categoryId: Long
)