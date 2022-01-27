package com.grappim.db.entities

import com.grappim.db.tables.ProductCategoriesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductCategoryEntity(
  id: EntityID<Long>
) : LongEntity(id) {
  companion object : LongEntityClass<ProductCategoryEntity>(ProductCategoriesTable)

  var name by ProductCategoriesTable.name
  var merchantId by ProductCategoriesTable.merchantId
  var stockId by ProductCategoriesTable.stockId
  var updatedOn by ProductCategoriesTable.updatedOn
  var createdOn by ProductCategoriesTable.createdOn

}