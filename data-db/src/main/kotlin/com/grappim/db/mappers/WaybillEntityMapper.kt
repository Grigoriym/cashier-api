package com.grappim.db.mappers

import com.grappim.db.entities.WaybillEntity
import com.grappim.domain.model.waybill.Waybill

fun WaybillEntity.toDomain(): Waybill =
  Waybill(
    id = this.id.value,
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

fun List<WaybillEntity>.toDomain(): List<Waybill> =
  this.map {
    it.toDomain()
  }