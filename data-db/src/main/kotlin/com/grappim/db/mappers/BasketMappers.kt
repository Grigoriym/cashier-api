package com.grappim.db.mappers

import com.grappim.db.entities.BasketEntity
import com.grappim.db.entities.BasketProductEntity
import com.grappim.domain.model.basket.Basket
import com.grappim.domain.model.basket.BasketProduct

fun BasketProductEntity.toDomain(): BasketProduct =
  BasketProduct(
    id = id.value,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basket.id.value
  )

fun BasketEntity.toDomain(): Basket =
  Basket(
    merchantId = merchantId.toString(),
    stockId = stockId.toString()
  )