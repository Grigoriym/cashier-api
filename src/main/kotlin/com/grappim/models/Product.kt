package com.grappim.models

import com.grappim.domain.ProductUnit
import com.grappim.serializers.BigDecimalSerializer
import com.grappim.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class Product(
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
    val updatedOn: LocalDateTime
)
