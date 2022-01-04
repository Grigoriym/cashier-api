package com.grappim.db.service

import com.grappim.db.entities.ProductCategoryEntity
import com.grappim.db.mappers.toProductCategory
import com.grappim.db.tables.ProductCategoriesTable
import com.grappim.domain.model.product_category.CreateProductCategory
import com.grappim.domain.model.product_category.ProductCategory
import com.grappim.domain.service.ProductCategoryService
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ProductCategoryServiceImpl : ProductCategoryService {

  override fun getAllProductCategoriesByMerchantId(
    merchantId: String
  ): List<ProductCategory> =
    transaction {
      val productCategories = ProductCategoryEntity.find {
        ProductCategoriesTable.merchantId eq UUID.fromString(merchantId)
      }

      productCategories.map {
        it.toProductCategory()
      }
    }

  override fun createProductCategory(createProductCategory: CreateProductCategory): Long =
    transaction {
      val newProductCategory = ProductCategoryEntity.new {
        this.name = createProductCategory.name
        this.stockId = UUID.fromString(createProductCategory.stockId)
        this.merchantId = UUID.fromString(createProductCategory.merchantId)
      }
      return@transaction newProductCategory.id.value
    }

}