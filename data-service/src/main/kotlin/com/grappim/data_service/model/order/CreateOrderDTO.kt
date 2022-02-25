package com.grappim.data_service.model.order

import com.grappim.utils.serializers.BigDecimalSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateOrderDTO(
  @SerialName("merchantId")
  val merchantId: String,
  @SerialName("stockId")
  val stockId: String,
  @SerialName("cashBoxId")
  val cashBoxId: String,
  @SerialName("totalSum")
  @Serializable(BigDecimalSerializer::class)
  val totalSum: BigDecimal,
  @SerialName("payType")
  val payType: String,
  @SerialName("orderItems")
  val orderItems: List<OrderItemDTO>
)