package com.grappim.data_service.model.cashbox

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CashBoxDTO(
  val name: String,
  @Serializable(UUIDSerializer::class)
  val cashBoxId: UUID,
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)

@Serializable
data class CashBoxResponseDTO(
  val cashBoxes: List<CashBoxDTO>
)

@Serializable
data class GetCashBoxesListDTO(
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID
)