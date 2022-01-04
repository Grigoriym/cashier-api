package com.grappim.db.mappers

import com.grappim.db.entities.StockEntity
import com.grappim.domain.model.stock.Stock

fun StockEntity.toStock(): Stock = Stock(
  id = id.value.toString(),
  merchantId = merchantId.toString(),
  name = stockName
)