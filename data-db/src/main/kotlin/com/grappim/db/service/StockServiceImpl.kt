package com.grappim.db.service

import com.grappim.db.entities.StockEntity
import com.grappim.db.mappers.toStock
import com.grappim.db.tables.StocksTable
import com.grappim.domain.model.stock.Stock
import com.grappim.domain.service.StockService
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class StockServiceImpl : StockService {

  override fun getStockById(uuid: UUID): Stock? = transaction {
    StockEntity.findById(uuid)?.toStock()
  }

  override fun getAllStocks(): List<Stock> =
    transaction {
      StockEntity.all().map(StockEntity::toStock)
    }

//    fun addStock(stock: StockToCreate) = transaction {
//        StockEntity.new {
//            this.stockName = stock.name
//            this.merchantId = stock.merchantId
//        }
//    }

  override fun deleteStock(stockId: String) = transaction {
    StockEntity[UUID.fromString(stockId)].delete()
  }

  override fun getStocksByMerchantId(merchantId: String): List<Stock> = transaction {
    StockEntity.find {
      StocksTable.merchantId eq UUID.fromString(merchantId)
    }.map { it.toStock() }
  }
}