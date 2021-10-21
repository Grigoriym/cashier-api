package com.grappim.service

import com.grappim.data.StockEntity
import com.grappim.models.Stock
import com.grappim.models.StockToCreate
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

    fun addStock(stock: StockToCreate) = transaction {
        StockEntity.new {
            this.stockName = stock.stockName
            this.merchantId = stock.merchantId
        }
    }

    fun deleteStock(stockId: String) = transaction {
        StockEntity[UUID.fromString(stockId)].delete()
    }
}