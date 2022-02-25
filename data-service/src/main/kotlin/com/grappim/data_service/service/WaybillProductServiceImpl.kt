package com.grappim.data_service.service

import com.grappim.db.entities.WaybillProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.WaybillProductTable
import com.grappim.domain.model.waybill.CreateWaybillProduct
import com.grappim.domain.model.waybill.FilterWaybillProduct
import com.grappim.domain.model.waybill.WaybillProduct
import com.grappim.domain.service.WaybillProductService
import com.grappim.utils.ProductDoesNotExist
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
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
  ): WaybillProduct = transaction {
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
    return@transaction newProduct.toDomain()
  }

  override fun updateProduct(updateProduct: WaybillProduct): WaybillProduct =
    transaction {
      WaybillProductTable.update({ WaybillProductTable.id eq updateProduct.id }) { product ->
        product[name] = updateProduct.name
        product[barcode] = updateProduct.barcode

        product[purchasePrice] = updateProduct.purchasePrice
        product[sellingPrice] = updateProduct.sellingPrice
        product[amount] = updateProduct.amount

        product[waybillId] = updateProduct.waybillId
        product[productId] = updateProduct.productId

        product[createdOn] = updateProduct.createdOn
        product[updatedOn] = LocalDateTime.now()
      }
      getWaybillProduct(updateProduct.id)
    }

  override fun deleteProduct(id: Long): Int = transaction {
    WaybillProductTable.deleteWhere {
      WaybillProductTable.id eq id
    }
  }

  private fun getWaybillProduct(
    id: Long
  ): WaybillProduct = transaction {
    val entity = WaybillProductEntity.findById(id) ?: throw ProductDoesNotExist()
    entity.toDomain()
  }
}