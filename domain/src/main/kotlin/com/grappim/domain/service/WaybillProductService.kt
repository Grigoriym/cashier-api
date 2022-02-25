package com.grappim.domain.service

import com.grappim.domain.model.waybill.CreateWaybillProduct
import com.grappim.domain.model.waybill.FilterWaybillProduct
import com.grappim.domain.model.waybill.WaybillProduct

interface WaybillProductService {

  fun filterProducts(filter: FilterWaybillProduct): List<WaybillProduct>

  fun createProduct(
    createWaybillProduct: CreateWaybillProduct
  ): WaybillProduct

  fun updateProduct(
    updateProduct: WaybillProduct
  ): WaybillProduct

  fun deleteProduct(
    id: Long
  ): Int
}