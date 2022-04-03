package com.grappim.domain.model.basket

import com.grappim.common_domain.model.ProductUnit
import java.math.BigDecimal
import java.util.*

data class BasketProduct(
  val id: Long,
  val barcode: String,
  val name: String,
  val productId: Long,
  val stockId: UUID,
  val merchantId: UUID,
  val amount: BigDecimal,
  val unit: ProductUnit,
  val sellingPrice: BigDecimal,
  val basketId: Long
)