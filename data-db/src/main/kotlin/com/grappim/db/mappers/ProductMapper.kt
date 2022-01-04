package com.grappim.db.mappers

import com.grappim.db.entities.ProductEntity
import com.grappim.domain.model.product.Product

fun ProductEntity.toProduct(): Product = Product(
  id = this.id.value,
  barcode = this.barcode,
  name = this.name,
  stockId = this.stockId.toString(),
  amount = this.amount,
  unit = this.unit,
  merchantId = this.merchantId.toString(),
  purchasePrice = this.purchasePrice,
  sellingPrice = this.sellingPrice,
  createdOn = this.createdOn,
  updatedOn = this.updatedOn,
  categoryId = this.categoryId
)