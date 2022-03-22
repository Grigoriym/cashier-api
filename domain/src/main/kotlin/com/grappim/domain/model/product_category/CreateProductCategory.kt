package com.grappim.domain.model.product_category

import java.util.UUID

data class CreateProductCategory(
  val name: String,
  val merchantId: UUID,
  val stockId: UUID
)
