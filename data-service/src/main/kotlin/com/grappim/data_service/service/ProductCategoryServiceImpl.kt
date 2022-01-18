package com.grappim.data_service.service

import com.grappim.db.entities.ProductCategoryEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.ProductCategoriesTable
import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.FilterProductCategoriesRequest
import com.grappim.domain.model.product_category.ProductCategory
import com.grappim.domain.service.ProductCategoryService
import com.grappim.utils.ProductCategoryDoesNotExist
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProductCategoryServiceImpl : ProductCategoryService, BaseService {

  override fun filterProductCategoriesByMerchantId(
    filter: FilterProductCategoriesRequest
  ): List<ProductCategory> =
    transaction {
      checkMerchantId(filter.merchantId)
      checkStockId(filter.stockId)

      val productCategories = ProductCategoryEntity.find {
        ProductCategoriesTable.merchantId eq UUID.fromString(filter.merchantId)
      }

      productCategories.map {
        it.toDomain()
      }
    }

  override fun createProductCategory(
    createProductCategory: CreateProductCategory
  ): Long =
    transaction {
      val newProductCategory = ProductCategoryEntity.new {
        this.name = createProductCategory.name
        this.stockId = createProductCategory.stockId.toUUID()
        this.merchantId = createProductCategory.merchantId.toUUID()
      }
      return@transaction newProductCategory.id.value
    }

  override fun updateUser(
    merchantId: String,
    productCategory: ProductCategory
  ): ProductCategory =
    transaction {
      val entity = ProductCategoryEntity
        .findById(productCategory.id) ?: throw ProductCategoryDoesNotExist()
      entity.apply {
        this.name = productCategory.name
        this.stockId = productCategory.stockId.toUUID()
        this.merchantId = productCategory.merchantId.toUUID()
      }.toDomain()
    }

  override fun deleteProductCategoryById(
    id: Long
  ): Int = transaction {
    ProductCategoriesTable.deleteWhere {
      ProductCategoriesTable.id eq id
    }
  }

}