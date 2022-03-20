package com.grappim.domain.model.cashbox

import java.util.UUID

data class GetCashBoxesList(
  val merchantId: UUID,
  val stockId: UUID
)