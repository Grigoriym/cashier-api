package com.grappim.data_service.model.product_category

import com.grappim.utils.serializers.LocalDateTimeSerializer
import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class ProductCategoryDTO(
  val id: Long,
  val name: String,
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID,
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
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)