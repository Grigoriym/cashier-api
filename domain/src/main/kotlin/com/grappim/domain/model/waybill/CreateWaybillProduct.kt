package com.grappim.domain.model.waybill

import java.math.BigDecimal

data class CreateWaybillProduct(
  val name: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val amount: BigDecimal,
  val barcode: String,
  val waybillId: Long,
  val productId: Long
)