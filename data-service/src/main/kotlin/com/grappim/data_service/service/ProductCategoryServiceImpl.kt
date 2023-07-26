package com.grappim.data_service.service

import com.grappim.db.entities.ProductCategoryEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.ProductCategoriesTable
import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.FilterProductCategoriesRequest
import com.grappim.domain.model.product_category.ProductCategory
import com.grappim.domain.service.ProductCategoryService
import com.grappim.utils.ProductCategoryDoesNotExist
import com.grappim.utils.ProductNameIsEmptyException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class ProductCategoryServiceImpl : ProductCategoryService, BaseService {

  override fun filterProductCategoriesByMerchantId(
    filter: FilterProductCategoriesRequest
  ): List<ProductCategory> =
    transaction {
      checkMerchantId(filter.merchantId)
      checkStockId(filter.stockId)

      val productCategories = ProductCategoryEntity.find(op = {
        (ProductCategoriesTable.merchantId eq filter.merchantId and
            (ProductCategoriesTable.stockId eq filter.stockId))
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
      checkName(createProductCategory.name)
      val newProductCategoryId = ProductCategoriesTable.insertAndGetId {
        it[name] = createProductCategory.name
        it[stockId] = createProductCategory.stockId
        it[merchantId] = createProductCategory.merchantId
        it[createdOn] = LocalDateTime.now()
        it[updatedOn] = LocalDateTime.now()
      }
      return@transaction newProductCategoryId.value
    }

  private fun checkName(name: String) {
    if (name.isEmpty()) {
      throw ProductNameIsEmptyException()
    }
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
        category[stockId] = productCategory.stockId
        category[merchantId] = productCategory.merchantId
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