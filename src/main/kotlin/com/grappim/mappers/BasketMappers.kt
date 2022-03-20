package com.grappim.mappers

import com.grappim.data_service.model.basket.AddBasketProductDTO
import com.grappim.data_service.model.basket.BasketProductDTO
import com.grappim.domain.model.basket.AddBasketProduct
import com.grappim.domain.model.basket.BasketProduct

fun BasketProduct.toDTO(): BasketProductDTO =
  BasketProductDTO(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
  )

fun BasketProductDTO.toDomain(): BasketProduct =
  BasketProduct(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice,
    basketId = basketId
  )

fun AddBasketProductDTO.toDomain(): AddBasketProduct =
  AddBasketProduct(
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit,
    sellingPrice = sellingPrice
  )