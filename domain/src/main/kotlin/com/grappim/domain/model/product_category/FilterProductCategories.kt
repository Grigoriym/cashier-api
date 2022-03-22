package com.grappim.domain.model.product_category

import java.util.UUID

data class FilterProductCategoriesRequest(
  val limit: Int,
  val offset: Long,
  val merchantId: UUID,
  val stockId: UUID
)