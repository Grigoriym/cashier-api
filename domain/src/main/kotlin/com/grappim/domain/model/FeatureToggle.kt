package com.grappim.domain.model

import java.util.UUID

data class FeatureToggle(
  val merchantId: UUID,
  val stockId: UUID,
  val isWaybillEnabled: Boolean,
  val isSalesEnabled: Boolean
)