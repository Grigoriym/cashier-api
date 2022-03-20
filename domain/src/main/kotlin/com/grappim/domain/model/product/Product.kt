package com.grappim.domain.model.product

import com.grappim.domain.model.general.ProductUnit
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Product(
  val id: Long,
  val barcode: String,
  val name: String,
  val stockId: UUID,
  val amount: BigDecimal,
  val unit: ProductUnit,
  val merchantId: UUID,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val createdOn: LocalDateTime,
  val updatedOn: LocalDateTime,
  val categoryId: Long
)