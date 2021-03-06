package com.grappim.mappers

import com.grappim.common_data.model.stock.StockDTO
import com.grappim.domain.model.stock.Stock

fun Stock.toDTO(): StockDTO = StockDTO(
  id = this.id,
  merchantId = this.merchantId,
  name = this.name
)

fun List<Stock>.toDTO(): List<StockDTO> =
  this.map {
    StockDTO(
      id = it.id,
      merchantId = it.merchantId,
      name = it.name
    )
  }