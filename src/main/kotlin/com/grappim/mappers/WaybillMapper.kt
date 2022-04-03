package com.grappim.mappers

import com.grappim.common_data.WaybillStatusDTO
import com.grappim.common_data.model.waybill.CreateWaybillDTO
import com.grappim.common_data.model.waybill.WaybillDTO
import com.grappim.common_domain.model.WaybillStatus
import com.grappim.domain.model.waybill.CreateWaybill
import com.grappim.domain.model.waybill.Waybill

fun WaybillDTO.toDomain(): Waybill =
  Waybill(
    id = this.id,
    merchantId = this.merchantId,
    stockId = this.stockId,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn,
    totalCost = this.totalCost,
    status = this.status.toDomain(),
    comment = this.comment,
    reservedTime = this.reservedTime,
    number = this.number
  )

fun List<Waybill>.toDTO(): List<WaybillDTO> =
  this.map {
    it.toDTO()
  }

fun Waybill.toDTO(): WaybillDTO =
  WaybillDTO(
    id = this.id,
    merchantId = this.merchantId,
    stockId = this.stockId,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn,
    totalCost = this.totalCost,
    status = this.status.toDTO(),
    comment = this.comment,
    reservedTime = this.reservedTime,
    number = this.number
  )

fun CreateWaybillDTO.toDomain(): CreateWaybill =
  CreateWaybill(
    merchantId = this.merchantId,
    stockId = this.stockId
  )

fun WaybillStatusDTO.toDomain(): WaybillStatus =
  when (this) {
    WaybillStatusDTO.ACTIVE -> WaybillStatus.ACTIVE
    WaybillStatusDTO.DRAFT -> WaybillStatus.DRAFT
  }

fun WaybillStatus.toDTO(): WaybillStatusDTO =
  when (this) {
    WaybillStatus.DRAFT -> WaybillStatusDTO.DRAFT
    WaybillStatus.ACTIVE -> WaybillStatusDTO.ACTIVE
  }