package com.grappim.mappers

import com.grappim.domain.model.product.CreateProduct
import com.grappim.model.CreateProductDTO

fun CreateProductDTO.toCreateProduct(): CreateProduct = CreateProduct(
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