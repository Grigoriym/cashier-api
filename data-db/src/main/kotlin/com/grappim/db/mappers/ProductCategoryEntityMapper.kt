package com.grappim.db.mappers

import com.grappim.db.entities.ProductCategoryEntity
import com.grappim.domain.model.product_category.ProductCategory

fun ProductCategoryEntity.toDomain(): ProductCategory =
  ProductCategory(
    id = this.id.value,
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId,
    updatedOn = this.updatedOn,
    createdOn = this.createdOn
  )