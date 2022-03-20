package com.grappim.domain.model.base

import java.util.*

data class BaseFilter(
  val limit: Int,
  val offset: Long,
  val merchantId: UUID,
  val stockId: UUID,
  val query: String? = null
)
