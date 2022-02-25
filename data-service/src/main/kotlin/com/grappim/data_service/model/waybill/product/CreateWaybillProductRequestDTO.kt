package com.grappim.data_service.model.waybill.product

import com.grappim.utils.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateWaybillProductRequestDTO(
  @SerialName("product")
  val product: CreateWaybillProductDTO
)

@Serializable
data class CreateWaybillProductDTO(
  @SerialName("id")
  val id: Long? = null,
  @SerialName("amount")
  @Serializable(BigDecimalSerializer::class)
  val amount: BigDecimal,
  @SerialName("barcode")
  val barcode: String,
  @SerialName("name")
  val name: String,
  @SerialName("purchasePrice")
  @Serializable(BigDecimalSerializer::class)
  val purchasePrice: BigDecimal,
  @SerialName("sellingPrice")
  @Serializable(BigDecimalSerializer::class)
  val sellingPrice: BigDecimal,
  @SerialName("waybillId")
  val waybillId: Long,
  @SerialName("productId")
  val productId: Long
)