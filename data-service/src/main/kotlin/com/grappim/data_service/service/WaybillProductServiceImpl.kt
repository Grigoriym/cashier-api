package com.grappim.data_service.service

import com.grappim.db.entities.WaybillEntity
import com.grappim.db.entities.WaybillProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.WaybillProductTable
import com.grappim.db.tables.WaybillTable
import com.grappim.domain.model.waybill.*
import com.grappim.domain.service.WaybillProductService
import com.grappim.utils.ProductDoesNotExist
import com.grappim.utils.WaybillDoesNotExist
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.math.BigDecimal
import java.time.LocalDateTime

class WaybillProductServiceImpl : WaybillProductService, BaseService {

  override fun filterProducts(
    filter: FilterWaybillProduct
  ): List<WaybillProduct> = transaction {
    val products = WaybillProductEntity.find(op = {
      WaybillProductTable.waybillId eq filter.waybillId
    }).orderBy(WaybillProductTable.updatedOn to SortOrder.DESC)
      .limit(
        n = filter.limit,
        offset = filter.offset
      )
    products.toList().toDomain()
  }

  override fun createProduct(
    createWaybillProduct: CreateWaybillProduct
  ): BigDecimal = transaction {
    val newProduct = WaybillProductEntity.new {
      this.name = createWaybillProduct.name
      this.barcode = createWaybillProduct.barcode

      this.purchasePrice = createWaybillProduct.purchasePrice
      this.sellingPrice = createWaybillProduct.sellingPrice
      this.amount = createWaybillProduct.amount

      this.waybillId = createWaybillProduct.waybillId
      this.productId = createWaybillProduct.productId

      this.createdOn = LocalDateTime.now()
      this.updatedOn = LocalDateTime.now()
    }

    val waybill = getWaybill(newProduct.waybillId)
    val totalCost = waybill.totalCost + (newProduct.purchasePrice * newProduct.amount)
    setNewTotalCost(waybill.id, totalCost)
    totalCost
  }

  private fun setNewTotalCost(
    waybillId: Long,
    newTotalCost: BigDecimal
  ) = transaction {
    WaybillTable.update({ WaybillTable.id eq waybillId }) { waybill ->
      waybill[totalCost] = newTotalCost
    }
  }

  override fun updateProduct(
    updateProduct: PartialWaybillProduct
  ): BigDecimal =
    transaction {
      WaybillProductTable.update({ WaybillProductTable.id eq updateProduct.id }) { product ->
        product[name] = updateProduct.name
        product[barcode] = updateProduct.barcode

        product[purchasePrice] = updateProduct.purchasePrice
        product[sellingPrice] = updateProduct.sellingPrice
        product[amount] = updateProduct.amount

        product[waybillId] = updateProduct.waybillId
        product[productId] = updateProduct.productId

        product[updatedOn] = LocalDateTime.now()
      }
      val waybill = getWaybill(updateProduct.waybillId)
      val totalCost = waybill.totalCost + (updateProduct.purchasePrice * updateProduct.amount)
      setNewTotalCost(waybill.id, totalCost)
      totalCost
    }

  override fun deleteProduct(id: Long): Int = transaction {
    WaybillProductTable.deleteWhere {
      WaybillProductTable.id eq id
    }
  }

  private fun getWaybill(waybillId: Long): Waybill = transaction {
    val entity = WaybillEntity
      .findById(waybillId) ?: throw WaybillDoesNotExist()
    entity.toDomain()
  }

  private fun getWaybillProduct(
    id: Long
  ): WaybillProduct = transaction {
    val entity = WaybillProductEntity.findById(id) ?: throw ProductDoesNotExist()
    entity.toDomain()
  }

  private fun getWaybillProduct(
    barcode: String
  ): WaybillProduct = transaction {
    val entity = WaybillProductEntity.find {
      WaybillProductTable.barcode eq barcode
    }.firstOrNull() ?: throw ProductDoesNotExist()
    entity.toDomain()
  }

  override fun getProduct(
    getWaybillProduct: GetWaybillProduct
  ): WaybillProduct = transaction {
    getWaybillProduct(getWaybillProduct.barcode)
  }
}