package com.grappim.data_service.model.waybill.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWaybillByBarcodeRequestDTO(
  @SerialName("barcode")
  val barcode: String,
  @SerialName("waybillId")
  val waybillId: Long
)
