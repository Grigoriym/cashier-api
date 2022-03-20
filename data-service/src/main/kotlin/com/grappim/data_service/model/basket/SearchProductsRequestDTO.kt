package com.grappim.data_service.model.basket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchProductsRequestDTO(
  @SerialName("stockId")
  val stockId: String,
  @SerialName("searchQuery")
  val searchQuery: String
)