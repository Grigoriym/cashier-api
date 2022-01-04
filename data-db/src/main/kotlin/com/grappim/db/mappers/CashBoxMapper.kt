package com.grappim.db.mappers

import com.grappim.db.entities.CashBoxEntity
import com.grappim.domain.model.cashbox.CashBox

fun CashBoxEntity.toCashBox(): CashBox = CashBox(
  name = name,
  cashBoxId = id.value.toString(),
  merchantId = merchantId.toString(),
  stockId = stockId.toString()
)