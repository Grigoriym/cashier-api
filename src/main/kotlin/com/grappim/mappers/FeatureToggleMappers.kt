package com.grappim.mappers

import com.grappim.data_service.model.feature_toggle.FeatureToggleDTO
import com.grappim.domain.model.FeatureToggle

fun FeatureToggle.toDTO(): FeatureToggleDTO =
  FeatureToggleDTO(
    merchantId = merchantId,
    stockId = stockId,
    isWaybillEnabled = isWaybillEnabled,
    isSalesEnabled = isSalesEnabled
  )