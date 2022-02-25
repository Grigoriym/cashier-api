package com.grappim.domain.model.waybill

import java.math.BigDecimal
import java.time.LocalDateTime

data class WaybillProduct(
  val id: Long,
  val name: String,
  val barcode: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val amount: BigDecimal,
  val waybillId: Long,
  val productId: Long,
  val createdOn: LocalDateTime,
  val updatedOn: LocalDateTime
)