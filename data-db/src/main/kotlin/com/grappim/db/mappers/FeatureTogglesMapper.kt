package com.grappim.db.mappers

import com.grappim.db.entities.FeatureToggleEntity
import com.grappim.domain.model.FeatureToggle

fun FeatureToggleEntity.toDomain(): FeatureToggle =
  FeatureToggle(
    merchantId = merchantId,
    stockId = stockId,
    isWaybillEnabled = isWaybillEnabled,
    isSalesEnabled = isSalesEnabled
  )