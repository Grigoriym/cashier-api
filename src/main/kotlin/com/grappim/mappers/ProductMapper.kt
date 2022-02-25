package com.grappim.mappers

import com.grappim.data_service.model.products.CreateProductDTO
import com.grappim.data_service.model.products.ProductDTO
import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.Product

fun CreateProductDTO.toDomain(): CreateProduct = CreateProduct(
  name = this.name,
  stockId = this.stockId,
  merchantId = this.merchantId,
  unit = this.unit,
  purchasePrice = this.purchasePrice,
  sellingPrice = this.sellingPrice,
  amount = this.amount,
  barcode = this.barcode,
  categoryId = this.categoryId
)

fun Product.toDTO(): ProductDTO = ProductDTO(
  name = this.name,
  stockId = this.stockId,
  merchantId = this.merchantId,
  unit = this.unit,
  purchasePrice = this.purchasePrice,
  sellingPrice = this.sellingPrice,
  amount = this.amount,
  barcode = this.barcode,
  categoryId = this.categoryId,
  id = this.id,
  createdOn = this.createdOn,
  updatedOn = this.updatedOn
)

fun List<Product>.toDTO(): List<ProductDTO> =
  this.map {
    it.toDTO()
  }

fun ProductDTO.toDomain(): Product =
  Product(
    name = this.name,
    stockId = this.stockId,
    merchantId = this.merchantId,
    unit = this.unit,
    purchasePrice = this.purchasePrice,
    sellingPrice = this.sellingPrice,
    amount = this.amount,
    barcode = this.barcode,
    categoryId = this.categoryId,
    id = this.id,
    createdOn = this.createdOn,
    updatedOn = this.updatedOn
  )