package com.grappim.data_service.service

import com.grappim.db.entities.ProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.ProductsTable
import com.grappim.domain.model.general.ProductUnit
import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.ProductService
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class ProductsServiceImpl : ProductService {

  override fun getAllProductsByMerchantId(merchantId: String): List<Product> =
    transaction {
      val products = ProductEntity.find {
        (ProductsTable.merchantId eq merchantId.toUUID())
      }

      products.map { it.toDomain() }
    }

  override fun createProduct(
    createProduct: CreateProduct
  ): Long = transaction {
    val newProduct = ProductEntity.new {
      this.barcode = createProduct.barcode
      this.name = createProduct.name
      this.stockId = createProduct.stockId.toUUID()
      this.amount = createProduct.amount
      this.unit = ProductUnit.getUnitFromString(createProduct.unit)
      this.merchantId = createProduct.merchantId.toUUID()
      this.purchasePrice = createProduct.purchasePrice
      this.sellingPrice = createProduct.sellingPrice
      this.createdOn = LocalDateTime.parse(createProduct.createdOn)
      this.updatedOn = LocalDateTime.parse(createProduct.updatedOn)
    }
    return@transaction newProduct.id.value
  }
}