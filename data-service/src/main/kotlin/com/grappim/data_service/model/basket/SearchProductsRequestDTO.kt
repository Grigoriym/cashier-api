package com.grappim.data_service.model.basket

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class SearchProductsRequestDTO(
  @SerialName("stockId")
  @Serializable(UUIDSerializer::class)
  val stockId: UUID,
  @SerialName("searchQuery")
  val searchQuery: String
)