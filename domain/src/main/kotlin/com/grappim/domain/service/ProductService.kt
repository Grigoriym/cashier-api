package com.grappim.domain.service

import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.FilterProductsRequest
import com.grappim.domain.model.product.Product

interface ProductService {

  fun filterProducts(filter: FilterProductsRequest): List<Product>

  fun createProduct(
    createProduct: CreateProduct
  ): Product

  fun updateProduct(
    updateProduct: Product
  ): Product
}