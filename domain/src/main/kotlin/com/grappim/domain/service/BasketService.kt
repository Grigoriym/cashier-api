package com.grappim.domain.service

import com.grappim.domain.model.product.Product

interface BasketService {

  fun addProductToBasket(
    product: Product
  )

  fun subtractProduct(
    product: Product
  )

  fun removeProductFromBasket(
    product: Product
  )

  fun removeBasket(): Int

}