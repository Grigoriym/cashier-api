package com.grappim.data_service.model.waybill

import com.grappim.domain.model.waybill.WaybillStatus
import com.grappim.utils.serializers.BigDecimalSerializer
import com.grappim.utils.serializers.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class WaybillDTO(
  @SerialName("id")
  val id: Long,
  @SerialName("merchantId")
  val merchantId: String,
  @SerialName("number")
  val number: String,
  @SerialName("stockId")
  val stockId: String,
  @SerialName("createdOn")
  @Serializable(LocalDateTimeSerializer::class)
  val createdOn: LocalDateTime,
  @SerialName("updatedOn")
  @Serializable(LocalDateTimeSerializer::class)
  val updatedOn: LocalDateTime,
  @SerialName("totalCost")
  @Serializable(BigDecimalSerializer::class)
  val totalCost: BigDecimal,
  @SerialName("status")
  val status: WaybillStatus,
  @SerialName("comment")
  val comment: String,
  @SerialName("reservedTime")
  @Serializable(LocalDateTimeSerializer::class)
  val reservedTime: LocalDateTime?
)
