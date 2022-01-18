package com.grappim.domain.model.product_category

data class FilterProductCategoriesRequest(
  val limit: Int,
  val offset: Int,
  val merchantId: String,
  val stockId: String
)