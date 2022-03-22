package com.grappim.domain.service

import com.grappim.domain.model.cashbox.AddCashBox
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList
import com.grappim.domain.model.stock.Stock
import java.util.*

interface CashBoxService {

  fun getCashBoxById(uuid: UUID): CashBox?

  fun getCashBoxes(
    getCashBoxesList: GetCashBoxesList
  ): List<CashBox>

  fun deleteCashBox(cashBoxId: String)

  fun createCashBox(addCashBox: AddCashBox): String
  fun createCashBox(
    merchantId: UUID,
    stock: Stock
  ): UUID

}