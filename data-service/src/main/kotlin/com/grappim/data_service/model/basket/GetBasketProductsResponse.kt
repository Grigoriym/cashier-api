package com.grappim.data_service.model.basket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBasketProductsResponse(
  @SerialName("products")
  val products: List<BasketProductDTO>
)
