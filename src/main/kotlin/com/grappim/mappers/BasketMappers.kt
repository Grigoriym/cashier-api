package com.grappim.mappers

import com.grappim.common_data.ProductUnitDTO
import com.grappim.common_data.model.basket.AddBasketProductDTO
import com.grappim.common_data.model.basket.BasketProductDTO
import com.grappim.common_domain.model.ProductUnit
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
    unit = unit.toDTO(),
    sellingPrice = sellingPrice,
    basketId = basketId
  )

fun ProductUnitDTO.toDomain(): ProductUnit =
  when (this) {
    ProductUnitDTO.KG -> ProductUnit.KG
    ProductUnitDTO.PIECE -> ProductUnit.PIECE
    ProductUnitDTO.LITRE -> ProductUnit.LITRE
    ProductUnitDTO.METRE -> ProductUnit.METRE
  }

fun ProductUnit.toDTO(): ProductUnitDTO =
  when (this) {
    ProductUnit.KG -> ProductUnitDTO.KG
    ProductUnit.PIECE -> ProductUnitDTO.PIECE
    ProductUnit.LITRE -> ProductUnitDTO.LITRE
    ProductUnit.METRE -> ProductUnitDTO.METRE
  }

fun BasketProductDTO.toDomain(): BasketProduct =
  BasketProduct(
    id = id,
    barcode = barcode,
    name = name,
    productId = productId,
    stockId = stockId,
    merchantId = merchantId,
    amount = amount,
    unit = unit.toDomain(),
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
    unit = unit.toDomain(),
    sellingPrice = sellingPrice
  )