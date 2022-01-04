package com.grappim.domain.service

import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList
import java.util.*

interface CashBoxService {

  fun getCashBoxById(uuid: UUID): CashBox?

  fun getCashBoxes(
    getCashBoxesList: GetCashBoxesList
  ): List<CashBox>

  fun deleteCashBox(cashBoxId: String)
}