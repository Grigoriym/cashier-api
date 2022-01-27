package com.grappim.domain.model.product

data class FilterProductsRequest(
  val limit: Int,
  val offset: Long,
  val merchantId: String,
  val stockId: String
)