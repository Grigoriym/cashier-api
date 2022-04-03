package com.grappim.mappers

import com.grappim.common_data.model.waybill.product.*
import com.grappim.domain.model.waybill.*

fun CreateWaybillProductDTO.toDomain(): CreateWaybillProduct =
  CreateWaybillProduct(
    name = this.name,
    barcode = this.barcode,

    purchasePrice = this.purchasePrice,
    sellingPrice = this.sellingPrice,
    amount = this.amount,

    waybillId = this.waybillId,
    productId = this.productId
  )

fun WaybillProduct.toDTO(): WaybillProductDTO =
  WaybillProductDTO(
    id = this.id,
    name = this.name,
    barcode = this.barcode,
    purchasePrice = this.purchasePrice,
    sellingPrice = this.sellingPrice,
    amount = this.amount,
    waybillId = this.waybillId,
    productId = this.productId,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn
  )

fun List<WaybillProduct>.toDTO(): List<WaybillProductDTO> =
  this.map {
    it.toDTO()
  }

fun WaybillProductDTO.toDomain(): WaybillProduct =
  WaybillProduct(
    id = this.id,
    name = this.name,
    barcode = this.barcode,
    purchasePrice = this.purchasePrice,
    sellingPrice = this.sellingPrice,
    amount = this.amount,
    waybillId = this.waybillId,
    productId = this.productId,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn
  )

fun FilterWaybillProductDTO.toDomain(): FilterWaybillProduct =
  FilterWaybillProduct(
    limit = this.limit,
    offset = this.offset,
    waybillId = this.waybillId
  )

fun GetWaybillByBarcodeRequestDTO.toDomain(): GetWaybillProduct =
  GetWaybillProduct(
    barcode = this.barcode,
    waybillId = this.waybillId
  )

fun PartialWaybillProductDTO.toDomain(): PartialWaybillProduct =
  PartialWaybillProduct(
    id = id,
    amount = amount,
    barcode = barcode,
    name = name,
    purchasePrice = purchasePrice,
    sellingPrice = sellingPrice,
    waybillId = waybillId,
    productId = productId
  )