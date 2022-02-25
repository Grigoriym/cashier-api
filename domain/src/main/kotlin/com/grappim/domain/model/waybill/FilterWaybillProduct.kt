package com.grappim.domain.model.waybill

data class FilterWaybillProduct(
  val limit: Int,
  val offset: Long,
  val waybillId: Long
)