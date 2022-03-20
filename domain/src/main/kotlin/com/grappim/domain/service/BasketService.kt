package com.grappim.domain.service

import com.grappim.domain.model.basket.AddBasketProduct
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.basket.SubtractBasketProduct
import com.grappim.domain.model.product.Product

interface BasketService {

  fun addProductToBasket(
    newProduct: AddBasketProduct
  ): BasketProduct

  fun subtractProduct(
    product: BasketProduct
  ): SubtractBasketProduct

  fun removeProductFromBasket(
    product: Product
  )

  fun getBasketProducts(
    merchantId: String,
    stockId: String
  ): List<BasketProduct>

  fun removeBasket(): Int

  fun searchProducts(
    merchantId: String,
    stockId: String,
    searchQuery: String
  ): List<BasketProduct>

}