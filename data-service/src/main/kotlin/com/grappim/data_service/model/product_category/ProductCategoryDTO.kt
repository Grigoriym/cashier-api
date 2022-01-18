package com.grappim.data_service.model.product_category

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryDTO(
  val id: Long,
  val name: String,
  val merchantId: String,
  val stockId: String
)

@Serializable
data class CreateProductCategoryRequestDTO(
  val category: CreateProductCategoryDTO
)

@Serializable
data class CreateProductCategoryDTO(
  val name: String,
  val merchantId: String,
  val stockId: String
)