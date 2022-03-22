package com.grappim.data_service.model.feature_toggle

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CreateFeatureToggleRequestDTO(
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)
