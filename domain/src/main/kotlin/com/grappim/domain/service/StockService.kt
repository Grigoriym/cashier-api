package com.grappim.domain.service

import com.grappim.domain.model.stock.Stock
import java.util.*

interface StockService {

  fun getStockById(uuid: UUID): Stock?

  fun getAllStocks(): List<Stock>

  fun deleteStock(stockId: String)

  fun getStocksByMerchantId(merchantId: String): List<Stock>

}