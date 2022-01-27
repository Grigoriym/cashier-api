package com.grappim.domain.model.product_category

import java.time.LocalDateTime

data class ProductCategory(
  val id: Long,
  val name: String,
  val merchantId: String,
  val stockId: String,
  val updatedOn: LocalDateTime,
  val createdOn: LocalDateTime
)
