package com.grappim.data_service.service

import com.grappim.db.entities.ProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.ProductsTable
import com.grappim.domain.model.general.ProductUnit
import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.FilterProductsRequest
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.ProductService
import com.grappim.utils.ProductDoesNotExist
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class ProductsServiceImpl : ProductService, BaseService {

  override fun filterProducts(filter: FilterProductsRequest): List<Product> =
    transaction {
      checkMerchantId(filter.merchantId)
      checkStockId(filter.stockId)

      val products = ProductEntity.find(op = {
        (ProductsTable.merchantId eq filter.merchantId.toUUID()) and
            (ProductsTable.stockId eq filter.stockId.toUUID())
      }).orderBy(ProductsTable.updatedOn to SortOrder.DESC)
        .limit(
          n = filter.limit,
          offset = filter.offset
        )

      products.map {
        it.toDomain()
      }
    }

  override fun createProduct(
    createProduct: CreateProduct
  ): Product = transaction {
    val newProduct = ProductEntity.new {
      this.barcode = createProduct.barcode
      this.name = createProduct.name
      this.stockId = createProduct.stockId.toUUID()
      this.amount = createProduct.amount
      this.unit = ProductUnit.getUnitFromString(createProduct.unit)
      this.merchantId = createProduct.merchantId.toUUID()
      this.purchasePrice = createProduct.purchasePrice
      this.sellingPrice = createProduct.sellingPrice
      this.createdOn = LocalDateTime.now()
      this.updatedOn = LocalDateTime.now()
      this.categoryId = createProduct.categoryId
    }
    return@transaction newProduct.toDomain()
  }

  override fun updateProduct(updateProduct: Product): Product = transaction {
    ProductsTable.update({ ProductsTable.id eq updateProduct.id }) { product ->
      product[id] = updateProduct.id
    }
    getProduct(updateProduct.id)
  }

  private fun getProduct(productId: Long): Product = transaction {
    val entity = ProductEntity.findById(productId) ?: throw ProductDoesNotExist()
    entity.toDomain()
  }
}