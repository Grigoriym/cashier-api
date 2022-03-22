package com.grappim.domain.model.cashbox

import java.util.UUID

data class CashBox(
  val name: String,
  val cashBoxId: UUID,
  val merchantId: UUID,
  val stockId: UUID
)
