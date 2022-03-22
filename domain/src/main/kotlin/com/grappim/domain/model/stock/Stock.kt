package com.grappim.domain.model.stock

import java.util.UUID

data class Stock(
  val id: UUID,
  val merchantId: UUID,
  val name: String
)
