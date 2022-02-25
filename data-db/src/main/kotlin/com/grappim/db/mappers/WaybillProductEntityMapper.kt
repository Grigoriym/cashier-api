package com.grappim.db.mappers

import com.grappim.db.entities.WaybillProductEntity
import com.grappim.domain.model.waybill.WaybillProduct

fun WaybillProductEntity.toDomain(): WaybillProduct =
  WaybillProduct(
    id = this.id.value,
    barcode = this.barcode,
    name = this.name,
    amount = this.amount,
    purchasePrice = this.purchasePrice,
    sellingPrice = this.sellingPrice,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn,
    waybillId = this.waybillId,
    productId = this.productId
  )

fun List<WaybillProductEntity>.toDomain(): List<WaybillProduct> =
  this.map {
    it.toDomain()
  }