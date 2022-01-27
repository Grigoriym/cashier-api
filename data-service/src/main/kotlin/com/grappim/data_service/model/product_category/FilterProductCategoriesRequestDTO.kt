package com.grappim.data_service.model.product_category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductCategoriesRequestDTO(
  @SerialName("limit")
  val limit: Int,
  @SerialName("offset")
  val offset: Long,
  @SerialName("merchantId")
  val merchantId: String,
  @SerialName("stockId")
  val stockId: String
)