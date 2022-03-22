package com.grappim.domain.model.waybill

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Waybill(
  val id: Long,
  val merchantId: UUID,
  val stockId: UUID,
  val createdOn: LocalDateTime,
  val updatedOn: LocalDateTime,
  val totalCost: BigDecimal,
  val status: WaybillStatus,
  val comment: String,
  val number: String,
  val reservedTime: LocalDateTime?
)
