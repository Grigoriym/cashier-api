package com.grappim.domain.service

import com.grappim.domain.model.base.BaseFilter
import com.grappim.domain.model.waybill.CreateWaybill
import com.grappim.domain.model.waybill.Waybill

interface WaybillService {

  fun filterWaybills(
    filter: BaseFilter
  ): List<Waybill>

  fun createWaybill(
    createWaybill: CreateWaybill
  ): Waybill

  fun updateWaybill(
    newWaybill: Waybill
  ) :Waybill

  fun deleteWaybill(
    id: Long
  ): Int

}