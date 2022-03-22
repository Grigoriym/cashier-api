package com.grappim.domain.service

import com.grappim.domain.model.FeatureToggle
import java.util.*

interface FeatureToggleService {

  fun getFeatureToggle(
    merchantId: UUID,
    stockId: UUID
  ): FeatureToggle

  fun createFeatureToggle(
    merchantId: UUID,
    stockId: UUID
  ): FeatureToggle

  fun updateFeatureToggle(
    featureToggle: FeatureToggle
  ): FeatureToggle
}