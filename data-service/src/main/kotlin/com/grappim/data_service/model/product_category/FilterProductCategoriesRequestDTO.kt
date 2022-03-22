package com.grappim.data_service.model.product_category

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class FilterProductCategoriesRequestDTO(
  @SerialName("limit")
  val limit: Int,
  @SerialName("offset")
  val offset: Long,
  @SerialName("merchantId")
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @SerialName("stockId")
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)