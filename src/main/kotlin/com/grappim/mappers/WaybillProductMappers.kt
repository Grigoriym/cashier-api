package com.grappim.mappers

import com.grappim.data_service.model.waybill.product.CreateWaybillProductDTO
import com.grappim.data_service.model.waybill.product.FilterWaybillProductDTO
import com.grappim.data_service.model.waybill.product.WaybillProductDTO
import com.grappim.domain.model.waybill.CreateWaybillProduct
import com.grappim.domain.model.waybill.FilterWaybillProduct
import com.grappim.domain.model.waybill.WaybillProduct

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