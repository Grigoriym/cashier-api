package com.grappim.domain.model.waybill

import java.math.BigDecimal
import java.time.LocalDateTime

data class Waybill(
  val id: Long,
  val merchantId: String,
  val stockId: String,
  val createdOn: LocalDateTime,
  val updatedOn: LocalDateTime,
  val totalCost: BigDecimal,
  val status: WaybillStatus,
  val comment: String,
  val number: String,
  val reservedTime: LocalDateTime?
)
