package com.grappim.data_service.model.product_category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterProductCategoriesResponseDTO(
  @SerialName("categories")
  val categories: List<ProductCategoryDTO>
) {
  companion object {
    fun empty(): FilterProductCategoriesResponseDTO =
      FilterProductCategoriesResponseDTO(emptyList())
  }
}