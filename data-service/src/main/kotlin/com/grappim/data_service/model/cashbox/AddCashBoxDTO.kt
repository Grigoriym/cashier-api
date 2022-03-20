package com.grappim.data_service.model.cashbox

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AddCashBoxDTO(
  @SerialName("name")
  val name: String,
  @SerialName("merchantId")
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @SerialName("stockId")
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)
