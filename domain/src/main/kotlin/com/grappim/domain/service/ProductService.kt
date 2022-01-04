package com.grappim.domain.service

import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.Product

interface ProductService {

  fun getAllProductsByMerchantId(merchantId: String): List<Product>

  fun createProduct(
    createProduct: CreateProduct
  ): Long
}