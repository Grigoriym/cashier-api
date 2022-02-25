package com.grappim.data_service.model.waybill.product

import com.grappim.utils.serializers.BigDecimalSerializer
import com.grappim.utils.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class WaybillProductDTO(
  val id: Long,
  val name: String,
  val barcode: String,
  @Serializable(with = BigDecimalSerializer::class)
  val purchasePrice: BigDecimal,
  @Serializable(with = BigDecimalSerializer::class)
  val sellingPrice: BigDecimal,
  @Serializable(with = BigDecimalSerializer::class)
  val amount: BigDecimal,
  val waybillId: Long,
  val productId: Long,
  @Serializable(with = LocalDateTimeSerializer::class)
  val createdOn: LocalDateTime,
  @Serializable(with = LocalDateTimeSerializer::class)
  val updatedOn: LocalDateTime
)