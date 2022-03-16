package com.grappim.domain.service

import com.grappim.domain.model.waybill.*
import java.math.BigDecimal

interface WaybillProductService {

  fun filterProducts(filter: FilterWaybillProduct): List<WaybillProduct>

  fun createProduct(
    createWaybillProduct: CreateWaybillProduct
  ): BigDecimal

  fun updateProduct(
    updateProduct: PartialWaybillProduct
  ): BigDecimal

  fun deleteProduct(
    id: Long
  ): Int

  fun getProduct(getWaybillProduct: GetWaybillProduct): WaybillProduct
}