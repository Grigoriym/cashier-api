package com.grappim.data_service.model.product_category

import com.grappim.utils.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ProductCategoryDTO(
  val id: Long,
  val name: String,
  val merchantId: String,
  val stockId: String,
  @Serializable(with = LocalDateTimeSerializer::class)
  val updatedOn: LocalDateTime,
  @Serializable(with = LocalDateTimeSerializer::class)
  val createdOn: LocalDateTime
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