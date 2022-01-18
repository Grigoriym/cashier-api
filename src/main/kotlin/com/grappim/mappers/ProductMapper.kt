package com.grappim.mappers

import com.grappim.data_service.model.CreateProductDTO
import com.grappim.domain.model.product.CreateProduct

fun CreateProductDTO.toDomain(): CreateProduct = CreateProduct(
  name = this.name,
  stockId = this.stockId,
  merchantId = this.merchantId,
  unit = this.unit,
  purchasePrice = this.purchasePrice,
  sellingPrice = this.sellingPrice,
  amount = this.amount,
  barcode = this.barcode,
  createdOn = this.createdOn,
  updatedOn = this.updatedOn,
  categoryId = this.categoryId
)