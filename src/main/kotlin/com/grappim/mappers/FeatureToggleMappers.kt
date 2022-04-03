package com.grappim.mappers

import com.grappim.common_data.model.feature_toggle.FeatureToggleDTO
import com.grappim.domain.model.FeatureToggle

fun FeatureToggle.toDTO(): FeatureToggleDTO =
  FeatureToggleDTO(
    merchantId = merchantId,
    stockId = stockId,
    isWaybillEnabled = isWaybillEnabled,
    isSalesEnabled = isSalesEnabled
  )