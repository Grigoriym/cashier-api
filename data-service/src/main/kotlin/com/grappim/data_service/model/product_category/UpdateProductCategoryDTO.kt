package com.grappim.data_service.model.product_category

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProductCategoryDTO(
  val productCategory: ProductCategoryDTO
)