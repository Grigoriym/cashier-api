package com.grappim.domain.model.waybill

import java.util.UUID

data class CreateWaybill(
  val merchantId: UUID,
  val stockId: UUID
)
