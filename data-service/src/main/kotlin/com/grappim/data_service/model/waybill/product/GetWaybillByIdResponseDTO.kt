package com.grappim.data_service.model.waybill.product

import com.grappim.data_service.model.waybill.WaybillDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillByIdResponseDTO(
  @SerialName("waybill")
  val waybill: WaybillDTO
)
