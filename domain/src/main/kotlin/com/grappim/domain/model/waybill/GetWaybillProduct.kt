package com.grappim.domain.model.waybill

data class GetWaybillProduct(
  val barcode: String,
  val waybillId: Long
)