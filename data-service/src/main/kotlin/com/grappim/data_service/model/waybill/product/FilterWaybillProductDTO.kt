package com.grappim.data_service.model.waybill.product

import kotlinx.serialization.Serializable

@Serializable
data class FilterWaybillProductDTO(
  val limit: Int,
  val offset: Long,
  val waybillId: Long
)