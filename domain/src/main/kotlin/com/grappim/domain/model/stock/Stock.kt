package com.grappim.domain.model.stock

import java.util.UUID

data class Stock(
  val id: String,
  val merchantId: UUID,
  val name: String
)
