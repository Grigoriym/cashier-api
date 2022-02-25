package com.grappim.mappers

import com.grappim.data_service.model.waybill.CreateWaybillDTO
import com.grappim.data_service.model.waybill.WaybillDTO
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
    status = this.status,
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
    status = this.status,
    comment = this.comment,
    reservedTime = this.reservedTime,
    number = this.number
  )

fun CreateWaybillDTO.toDomain(): CreateWaybill =
  CreateWaybill(
    merchantId = this.merchantId,
    stockId = this.stockId
  )