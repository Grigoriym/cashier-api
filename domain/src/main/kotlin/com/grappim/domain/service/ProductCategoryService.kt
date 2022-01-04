package com.grappim.domain.service

import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.ProductCategory

interface ProductCategoryService {

  fun getAllProductCategoriesByMerchantId(
    merchantId: String
  ): List<ProductCategory>

  fun createProductCategory(
    createProductCategory: CreateProductCategory
  ): Long
}