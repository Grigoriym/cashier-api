package com.grappim.db.entities

import com.grappim.db.tables.StocksTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class StockEntity(
  id: EntityID<UUID>
) : UUIDEntity(id) {
  companion object : UUIDEntityClass<StockEntity>(StocksTable)

  var merchantId by StocksTable.merchantId
  var stockName by StocksTable.stockName

}