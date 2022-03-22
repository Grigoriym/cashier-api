package com.grappim.db.mappers

import com.grappim.db.entities.CashBoxEntity
import com.grappim.domain.model.cashbox.CashBox

fun CashBoxEntity.toDomain(): CashBox = CashBox(
  name = name,
  cashBoxId = id.value,
  merchantId = merchantId,
  stockId = stockId
)