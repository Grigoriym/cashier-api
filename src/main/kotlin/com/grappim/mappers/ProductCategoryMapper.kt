package com.grappim.mappers

import com.grappim.data_service.model.product_category.CreateProductCategoryDTO
import com.grappim.data_service.model.product_category.FilterProductCategoriesRequestDTO
import com.grappim.data_service.model.product_category.ProductCategoryDTO
import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.FilterProductCategoriesRequest
import com.grappim.domain.model.product_category.ProductCategory

fun ProductCategoryDTO.toDomain(): ProductCategory =
  ProductCategory(
    id = this.id,
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId,
    updatedOn = this.updatedOn,
    createdOn = this.createdOn
  )

fun ProductCategory.toDTO(): ProductCategoryDTO =
  ProductCategoryDTO(
    id = this.id,
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId,
    updatedOn = this.updatedOn,
    createdOn = this.createdOn
  )

fun List<ProductCategory>.toDTO(): List<ProductCategoryDTO> =
  this.map {
    it.toDTO()
  }

fun CreateProductCategoryDTO.toDomain(): CreateProductCategory =
  CreateProductCategory(
    name = this.name,
    merchantId = this.merchantId,
    stockId = this.stockId
  )

fun FilterProductCategoriesRequestDTO.toDomain(): FilterProductCategoriesRequest =
  FilterProductCategoriesRequest(
    limit = this.limit,
    offset = this.offset,
    merchantId = this.merchantId,
    stockId = this.stockId
  )