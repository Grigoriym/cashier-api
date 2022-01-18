package com.grappim.data_service.model.cashbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddCashBoxDTO(
  @SerialName("name")
  val name: String,
  @SerialName("merchantId")
  val merchantId: String,
  @SerialName("stockId")
  val stockId: String
)
