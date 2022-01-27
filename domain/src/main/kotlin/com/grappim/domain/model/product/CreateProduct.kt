package com.grappim.domain.model.product

import java.math.BigDecimal

data class CreateProduct(
  val name: String,
  val stockId: String,
  val merchantId: String,
  val unit: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val amount: BigDecimal,
  val barcode: String,
  val categoryId: Long
)