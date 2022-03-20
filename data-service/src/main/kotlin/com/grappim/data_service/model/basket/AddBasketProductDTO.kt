package com.grappim.data_service.model.basket

import com.grappim.domain.model.general.ProductUnit
import com.grappim.utils.serializers.BigDecimalSerializer
import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.*

@Serializable
data class AddBasketProductDTO(
  val barcode: String,
  val name: String,
  val productId: Long,
  @Serializable(UUIDSerializer::class)
  val stockId: UUID,
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
  @Serializable(BigDecimalSerializer::class)
  val amount: BigDecimal,
  val unit: ProductUnit,
  @Serializable(BigDecimalSerializer::class)
  val sellingPrice: BigDecimal
)