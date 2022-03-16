package com.grappim.data_service.model.waybill.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillProductResponseDTO(
  @SerialName("product")
  val product: WaybillProductDTO
)