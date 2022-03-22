package com.grappim.domain.model.cashbox

import java.util.UUID

data class AddCashBox(
  val name: String,
  val merchantId: UUID,
  val stockId: UUID
)
