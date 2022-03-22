package com.grappim.domain.model.product_category

import java.time.LocalDateTime
import java.util.UUID

data class ProductCategory(
  val id: Long,
  val name: String,
  val merchantId: UUID,
  val stockId: UUID,
  val updatedOn: LocalDateTime,
  val createdOn: LocalDateTime
)
