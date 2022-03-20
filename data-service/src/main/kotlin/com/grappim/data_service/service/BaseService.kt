package com.grappim.data_service.service

import com.grappim.utils.MerchantIdIsEmpty
import com.grappim.utils.StockIdIsEmpty
import java.util.UUID

interface BaseService {

  fun checkMerchantId(
    merchantId: UUID
  ) {
    if (merchantId.toString().isEmpty()) {
      throw MerchantIdIsEmpty()
    }
  }

  fun checkStockId(
    stockId: UUID
  ) {
    if (stockId.toString().isEmpty()) {
      throw StockIdIsEmpty()
    }
  }

  fun checkStockIdAndMerchantId(
    merchantId: UUID,
    stockId: UUID
  ) {
    checkMerchantId(merchantId)
    checkStockId(stockId)
  }

}