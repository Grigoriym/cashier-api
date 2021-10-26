package com.grappim.service

import com.grappim.data.StockEntity
import com.grappim.data.Stocks
import com.grappim.models.Stock
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class StockService {

    fun getStockById(uuid: UUID): Stock? = transaction {
        StockEntity.findById(uuid)?.toStock()
    }

    fun getAllStocks(): List<Stock> =
        transaction {
            StockEntity.all().map(StockEntity::toStock)
        }

//    fun addStock(stock: StockToCreate) = transaction {
//        StockEntity.new {
//            this.stockName = stock.name
//            this.merchantId = stock.merchantId
//        }
//    }

    fun deleteStock(stockId: String) = transaction {
        StockEntity[UUID.fromString(stockId)].delete()
    }

    fun getStocksByMerchantId(merchantId: String): List<Stock> = transaction {
        StockEntity.find {
            Stocks.merchantId eq UUID.fromString(merchantId)
        }.map { it.toStock() }
    }
}