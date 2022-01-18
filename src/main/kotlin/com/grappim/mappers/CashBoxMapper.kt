package com.grappim.mappers

import com.grappim.data_service.model.cashbox.AddCashBoxDTO
import com.grappim.data_service.model.cashbox.CashBoxDTO
import com.grappim.data_service.model.cashbox.GetCashBoxesListDTO
import com.grappim.domain.model.cashbox.AddCashBox
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList

fun CashBox.toDTO(): CashBoxDTO = CashBoxDTO(
  name = this.name,
  cashBoxId = this.cashBoxId,
  merchantId = this.merchantId,
  stockId = this.stockId
)

fun List<CashBox>.toDTO(): List<CashBoxDTO> =
  this.map {
    CashBoxDTO(
      name = it.name,
      merchantId = it.merchantId,
      cashBoxId = it.cashBoxId,
      stockId = it.stockId
    )
  }

fun GetCashBoxesListDTO.toDomain(): GetCashBoxesList =
  GetCashBoxesList(
    merchantId = this.merchantId,
    stockId = this.stockId
  )

fun AddCashBoxDTO.toDomain(): AddCashBox =
  AddCashBox(
    name = name,
    merchantId = merchantId,
    stockId = stockId
  )