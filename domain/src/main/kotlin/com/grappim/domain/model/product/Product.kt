package com.grappim.domain.model.product

import com.grappim.domain.model.general.ProductUnit
import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
  val id: Long,
  val barcode: String,
  val name: String,
  val stockId: String,
  val amount: BigDecimal,
  val unit: ProductUnit,
  val merchantId: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val createdOn: LocalDateTime,
  val updatedOn: LocalDateTime,
  val categoryId: Long
)