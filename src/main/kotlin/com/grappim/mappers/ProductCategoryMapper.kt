package com.grappim.mappers

import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.ProductCategory
import com.grappim.model.CreateProductCategoryDTO
import com.grappim.model.ProductCategoryDTO

fun ProductCategoryDTO.toProductCategory(): ProductCategory =
  ProductCategory(
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId
  )

fun CreateProductCategoryDTO.toCreateProductCategory(): CreateProductCategory =
  CreateProductCategory(
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId
  )