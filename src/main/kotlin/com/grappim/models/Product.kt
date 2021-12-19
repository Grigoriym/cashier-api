package com.grappim.models

import com.grappim.domain.ProductUnit
import com.grappim.serializers.BigDecimalSerializer
import com.grappim.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class Product(
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
data class UpdateProductRequest(
    val product: Product
)

@Serializable
data class CreateProductRequest(
    val product: CreateProduct
)

@Serializable
data class CreateProductResponse(
    val id: Long
)

@Serializable
data class CreateProduct(
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
    val createdOn: String,
    val updatedOn: String,
    val categoryId: Int
)