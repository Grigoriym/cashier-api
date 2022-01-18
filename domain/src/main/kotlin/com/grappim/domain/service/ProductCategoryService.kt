package com.grappim.domain.service

import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.FilterProductCategoriesRequest
import com.grappim.domain.model.product_category.ProductCategory

interface ProductCategoryService {

  fun filterProductCategoriesByMerchantId(
    filter: FilterProductCategoriesRequest
  ): List<ProductCategory>

  fun createProductCategory(
    createProductCategory: CreateProductCategory
  ): Long

  fun updateUser(
    merchantId: String,
    productCategory: ProductCategory
  ): ProductCategory

  fun deleteProductCategoryById(
    id: Long
  ): Int
}