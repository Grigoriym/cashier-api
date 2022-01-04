package com.grappim.mappers

import com.grappim.db.entities.StockEntity
import com.grappim.model.StockDTO
import com.grappim.domain.model.stock.Stock

fun StockEntity.toStockDTO(): StockDTO = StockDTO(
  id = id.value.toString(),
  merchantId = merchantId.toString(),
  name = stockName
)

fun Stock.toStockDTO(): StockDTO = StockDTO(
  id = this.id,
  merchantId = this.merchantId,
  name = this.name
)

fun List<Stock>.toListStockDTO(): List<StockDTO> =
  this.map {
    StockDTO(
      id = it.id,
      merchantId = it.merchantId,
      name = it.name
    )
  }