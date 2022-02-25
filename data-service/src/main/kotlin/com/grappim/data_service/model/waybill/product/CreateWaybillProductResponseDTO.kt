package com.grappim.data_service.model.waybill.product

import com.grappim.utils.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateWaybillProductResponseDTO(
  @SerialName("totalCost")
  @Serializable(with = BigDecimalSerializer::class)
  val totalCost: BigDecimal,
  @SerialName("product")
  val product: WaybillProductDTO
)
