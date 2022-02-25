package com.grappim.data_service.model.waybill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWaybillDTO(
  @SerialName("merchantId")
  val merchantId: String,
  @SerialName("stockId")
  val stockId: String
)
