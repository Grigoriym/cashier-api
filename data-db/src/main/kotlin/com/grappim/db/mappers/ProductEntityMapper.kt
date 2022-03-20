package com.grappim.db.mappers

import com.grappim.db.entities.ProductEntity
import com.grappim.domain.model.product.Product

fun ProductEntity.toDomain(): Product = Product(
  id = this.id.value,
  barcode = this.barcode,
  name = this.name,
  stockId = this.stockId,
  amount = this.amount,
  unit = this.unit,
  merchantId = this.merchantId,
  purchasePrice = this.purchasePrice,
  sellingPrice = this.sellingPrice,
  createdOn = this.createdOn,
  updatedOn = this.updatedOn,
  categoryId = this.categoryId
)

fun List<ProductEntity>.toDomain(): List<Product> =
  this.map {
    it.toDomain()
  }