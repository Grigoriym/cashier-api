package com.grappim.db.mappers

import com.grappim.db.entities.StockEntity
import com.grappim.domain.model.stock.Stock

fun StockEntity.toDomain(): Stock = Stock(
  id = id.value.toString(),
  merchantId = merchantId,
  name = stockName
)