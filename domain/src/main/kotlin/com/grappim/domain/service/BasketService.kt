package com.grappim.domain.service

import com.grappim.domain.model.basket.AddBasketProduct
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.basket.SubtractBasketProduct
import com.grappim.domain.model.product.Product
import java.util.*

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
    merchantId: UUID,
    stockId: UUID
  ): List<BasketProduct>

  fun removeBasket(): Int

  fun searchProducts(
    merchantId: UUID,
    stockId: UUID,
    searchQuery: String
  ): List<BasketProduct>

}