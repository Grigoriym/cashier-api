package com.grappim.domain.model.waybill

import java.math.BigDecimal

data class PartialWaybillProduct(
  val id: Long? = null,
  val amount: BigDecimal,
  val barcode: String,
  val name: String,
  val purchasePrice: BigDecimal,
  val sellingPrice: BigDecimal,
  val waybillId: Long,
  val productId: Long
)