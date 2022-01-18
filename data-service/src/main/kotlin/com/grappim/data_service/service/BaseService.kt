package com.grappim.data_service.service

import com.grappim.utils.MerchantIdIsEmpty
import com.grappim.utils.StockIdIsEmpty

interface BaseService {

  fun checkMerchantId(
    merchantId: String
  ) {
    if (merchantId.isEmpty()) {
      throw MerchantIdIsEmpty()
    }
  }

  fun checkStockId(
    stockId: String
  ) {
    if (stockId.isEmpty()) {
      throw StockIdIsEmpty()
    }
  }

}