package com.grappim.data_service.service

import com.grappim.common_domain.model.ProductUnit
import com.grappim.db.entities.ProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.ProductsTable
import com.grappim.domain.model.base.BaseFilter
import com.grappim.domain.model.product.CreateProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.ProductService
import com.grappim.utils.DuplicateProductBarcodeException
import com.grappim.utils.DuplicateProductNameException
import com.grappim.utils.ProductDoesNotExist
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class ProductsServiceImpl : ProductService, BaseService {

  override fun filterProducts(filter: BaseFilter): List<Product> =
    transaction {
      checkStockIdAndMerchantId(filter.merchantId, filter.stockId)
      val query = filter.query
      val op: SqlExpressionBuilder.() -> Op<Boolean> = if (query == null || query.isEmpty()) {
        {
          (ProductsTable.merchantId eq filter.merchantId) and
              (ProductsTable.stockId eq filter.stockId)
        }
      } else {
        {
          (ProductsTable.merchantId eq filter.merchantId) and
              (ProductsTable.stockId eq filter.stockId) and
              (ProductsTable.name eq query)
        }
      }

      val products = ProductEntity.find(op = op).orderBy(ProductsTable.updatedOn to SortOrder.DESC)
        .limit(
          n = filter.limit,
          offset = filter.offset
        )

      products.toList().toDomain()
    }

  override fun createProduct(
    createProduct: CreateProduct
  ): Product = transaction {
    checkForName(createProduct.name)
    checkForBarcode(createProduct.barcode)

    val newProduct = ProductEntity.new {
      this.barcode = createProduct.barcode
      this.name = createProduct.name
      this.unit = ProductUnit.getUnitFromString(createProduct.unit)
      this.categoryId = createProduct.categoryId

      this.stockId = createProduct.stockId
      this.merchantId = createProduct.merchantId

      this.amount = createProduct.amount
      this.purchasePrice = createProduct.purchasePrice
      this.sellingPrice = createProduct.sellingPrice

      this.createdOn = LocalDateTime.now()
      this.updatedOn = LocalDateTime.now()
    }
    newProduct.toDomain()
  }

  private fun checkForName(name: String) {
    val foundProduct = ProductEntity.find {
      (ProductsTable.name eq name)
    }.firstOrNull()
    if (foundProduct != null) {
      throw DuplicateProductNameException(
        name = name
      )
    }
  }

  private fun checkForBarcode(barcode: String) {
    val foundProduct = ProductEntity.find {
      ProductsTable.barcode eq barcode
    }.firstOrNull()
    if (foundProduct != null) {
      throw DuplicateProductBarcodeException(
        barcode = barcode
      )
    }
  }

  override fun updateProduct(updateProduct: Product): Product = transaction {
    ProductsTable.update({ ProductsTable.id eq updateProduct.id }) { product ->
      product[name] = updateProduct.name
      product[barcode] = updateProduct.barcode
      product[unit] = updateProduct.unit
      product[categoryId] = updateProduct.categoryId

      product[stockId] = updateProduct.stockId
      product[merchantId] = updateProduct.merchantId

      product[amount] = updateProduct.amount
      product[purchasePrice] = updateProduct.purchasePrice
      product[sellingPrice] = updateProduct.sellingPrice

      product[createdOn] = updateProduct.createdOn
      product[updatedOn] = LocalDateTime.now()
    }
    getProduct(updateProduct.id)
  }

  override fun deleteProduct(id: Long): Int = transaction {
    ProductsTable.deleteWhere {
      ProductsTable.id eq id
    }
  }

  private fun getProduct(productId: Long): Product = transaction {
    val entity = ProductEntity.findById(productId) ?: throw ProductDoesNotExist()
    entity.toDomain()
  }
}