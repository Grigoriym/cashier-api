package com.grappim.db.service

import com.grappim.db.entities.ProductEntity
import com.grappim.db.mappers.toProduct
import com.grappim.db.tables.ProductsTable
import com.grappim.domain.model.general.ProductUnit
import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.ProductService
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*

class ProductsServiceImpl : ProductService {

//  fun getProductById(id: Long): ProductDTO = transaction {
//    ProductEntity.findById(id)?.toProductDTO() ?: throw ProductNotFound
//  }

  override fun getAllProductsByMerchantId(merchantId: String): List<Product> =
    transaction {
      val products = ProductEntity.find {
        (ProductsTable.merchantId eq UUID.fromString(merchantId))
      }

      products.map { it.toProduct() }
    }

  override fun createProduct(
    createProduct: CreateProduct
  ): Long = transaction {
    val newProduct = ProductEntity.new {
      this.barcode = createProduct.barcode
      this.name = createProduct.name
      this.stockId = UUID.fromString(createProduct.stockId)
      this.amount = createProduct.amount
      this.unit = ProductUnit.getUnitFromString(createProduct.unit)
      this.merchantId = UUID.fromString(createProduct.merchantId)
      this.purchasePrice = createProduct.purchasePrice
      this.sellingPrice = createProduct.sellingPrice
      this.createdOn = LocalDateTime.parse(createProduct.createdOn)
      this.updatedOn = LocalDateTime.parse(createProduct.updatedOn)
    }
    return@transaction newProduct.id.value
  }
}