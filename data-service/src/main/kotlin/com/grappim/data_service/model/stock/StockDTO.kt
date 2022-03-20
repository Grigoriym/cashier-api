package com.grappim.data_service.model.stock

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class StockDTO(
  val id: String,
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  val name: String
)

@Serializable
data class StocksResponseDTO(
  val stocks: List<StockDTO>
)