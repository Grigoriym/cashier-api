package com.grappim.data_service.service

import com.grappim.db.entities.StockEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.StocksTable
import com.grappim.domain.model.stock.Stock
import com.grappim.domain.service.StockService
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class StockServiceImpl : StockService {

  override fun getStockById(uuid: UUID): Stock? = transaction {
    StockEntity.findById(uuid)?.toDomain()
  }

  override fun getAllStocks(): List<Stock> =
    transaction {
      StockEntity.all().map(StockEntity::toDomain)
    }

  override fun deleteStock(stockId: String) = transaction {
    StockEntity[UUID.fromString(stockId)].delete()
  }

  override fun getStocksByMerchantId(merchantId: String): List<Stock> = transaction {
    StockEntity.find {
      StocksTable.merchantId eq UUID.fromString(merchantId)
    }.map { it.toDomain() }
  }
}