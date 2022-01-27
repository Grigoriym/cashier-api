package com.grappim.data_service.model.products

import com.grappim.domain.model.general.ProductUnit
import com.grappim.utils.serializers.BigDecimalSerializer
import com.grappim.utils.serializers.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class ProductDTO(
  val id: Long,
  val barcode: String,
  val name: String,
  val stockId: String,
  @Serializable(with = BigDecimalSerializer::class)
  val amount: BigDecimal,
  val unit: ProductUnit,
  val merchantId: String,
  @Serializable(with = BigDecimalSerializer::class)
  val purchasePrice: BigDecimal,
  @Serializable(with = BigDecimalSerializer::class)
  val sellingPrice: BigDecimal,
  @Serializable(with = LocalDateTimeSerializer::class)
  val createdOn: LocalDateTime,
  @Serializable(with = LocalDateTimeSerializer::class)
  val updatedOn: LocalDateTime,
  val categoryId: Long
)

@Serializable
data class UpdateProductRequestDTO(
  val product: ProductDTO
)

@Serializable
data class CreateProductRequestDTO(
  val product: CreateProductDTO
)

@Serializable
data class CreateProductResponseDTO(
  @SerialName("product")
  val product: ProductDTO
)

@Serializable
data class CreateProductDTO(
  val name: String,
  val stockId: String,
  val merchantId: String,
  val unit: String,
  @Serializable(with = BigDecimalSerializer::class)
  val purchasePrice: BigDecimal,
  @Serializable(with = BigDecimalSerializer::class)
  val sellingPrice: BigDecimal,
  @Serializable(with = BigDecimalSerializer::class)
  val amount: BigDecimal,
  val barcode: String,
  val categoryId: Long
)