package com.grappim.domain.model.basket

import com.grappim.domain.model.general.ProductUnit
import java.math.BigDecimal
import java.util.*

data class AddBasketProduct(
  val barcode: String,
  val name: String,
  val productId: Long,
  val stockId: UUID,
  val merchantId: UUID,
  val amount: BigDecimal,
  val unit: ProductUnit,
  val sellingPrice: BigDecimal
)