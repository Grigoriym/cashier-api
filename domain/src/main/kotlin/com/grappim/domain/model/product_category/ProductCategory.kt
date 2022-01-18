package com.grappim.domain.model.product_category

data class ProductCategory(
  val id: Long,
  val name: String,
  val merchantId: String,
  val stockId: String
)
