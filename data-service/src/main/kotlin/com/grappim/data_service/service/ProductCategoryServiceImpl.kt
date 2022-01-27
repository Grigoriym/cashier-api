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
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class ProductCategoryServiceImpl : ProductCategoryService, BaseService {

  override fun filterProductCategoriesByMerchantId(
    filter: FilterProductCategoriesRequest
  ): List<ProductCategory> =
    transaction {
      checkMerchantId(filter.merchantId)
      checkStockId(filter.stockId)

      val productCategories = ProductCategoryEntity.find(op = {
        (ProductCategoriesTable.merchantId eq filter.merchantId.toUUID() and
            (ProductCategoriesTable.stockId eq filter.stockId.toUUID()))
      }).orderBy(ProductCategoriesTable.updatedOn to SortOrder.DESC)
        .limit(
          n = filter.limit,
          offset = filter.offset
        )

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
        this.createdOn = LocalDateTime.now()
        this.updatedOn = LocalDateTime.now()
      }
      return@transaction newProductCategory.id.value
    }

  private fun getCategory(categoryId: Long): ProductCategory = transaction {
    val entity = ProductCategoryEntity
      .findById(categoryId) ?: throw ProductCategoryDoesNotExist()
    entity.toDomain()
  }

  override fun updateCategory(
    productCategory: ProductCategory
  ): ProductCategory =
    transaction {
      ProductCategoriesTable.update({ ProductCategoriesTable.id eq productCategory.id }) { category ->
        category[name] = productCategory.name
        category[stockId] = productCategory.stockId.toUUID()
        category[merchantId] = productCategory.merchantId.toUUID()
        category[updatedOn] = LocalDateTime.now()
        category[createdOn] = productCategory.createdOn
      }
      getCategory(productCategory.id)
    }

  override fun deleteProductCategoryById(
    id: Long
  ): Int = transaction {
    ProductCategoriesTable.deleteWhere {
      ProductCategoriesTable.id eq id
    }
  }

}