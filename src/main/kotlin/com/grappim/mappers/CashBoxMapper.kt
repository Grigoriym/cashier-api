package com.grappim.mappers

import com.grappim.db.entities.CashBoxEntity
import com.grappim.model.CashBoxDTO
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList
import com.grappim.model.GetCashBoxesListDTO

fun CashBoxEntity.toCashBoxDTO(): CashBoxDTO = CashBoxDTO(
  name = name,
  cashBoxId = id.value.toString(),
  merchantId = merchantId.toString(),
  stockId = stockId.toString()
)

fun CashBox.toCashBoxDTO(): CashBoxDTO = CashBoxDTO(
  name = this.name,
  cashBoxId = this.cashBoxId,
  merchantId = this.merchantId,
  stockId = this.stockId
)

fun List<CashBox>.toCashBoxListDTO(): List<CashBoxDTO> =
  this.map {
    CashBoxDTO(
      name = it.name,
      merchantId = it.merchantId,
      cashBoxId = it.cashBoxId,
      stockId = it.stockId
    )
  }

fun GetCashBoxesListDTO.toGetCashBoxesList(): GetCashBoxesList =
  GetCashBoxesList(
    merchantId = this.merchantId,
    stockId = this.stockId
  )