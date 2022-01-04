package com.grappim.db.mappers

import com.grappim.db.entities.ProductCategoryEntity
import com.grappim.domain.model.product_category.ProductCategory

fun ProductCategoryEntity.toProductCategory(): ProductCategory =
  ProductCategory(
    name = this.name,
    merchantId = this.merchantId.toString(),
    stockId = this.stockId.toString()
  )