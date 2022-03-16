package com.grappim.domain.model.basket

import com.grappim.domain.model.product.Product

data class Basket(
  val merchantId: String,
  val stockId: String,
  val cashBoxId: String,
  val items: List<Product>
)