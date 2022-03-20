package com.grappim.domain.model.base

data class BaseFilter(
  val limit: Int,
  val offset: Long,
  val merchantId: String,
  val stockId: String,
  val query: String? = null
)
