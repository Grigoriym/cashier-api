package com.grappim.data_service.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductsResponseDTO(
  @SerialName("products")
  val products: List<ProductDTO>
)
