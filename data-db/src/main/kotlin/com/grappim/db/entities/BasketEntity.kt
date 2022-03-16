package com.grappim.db.entities

import com.grappim.db.tables.BasketTable
import com.grappim.db.tables.ProductsTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BasketEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<BasketEntity>(BasketTable)

  var merchantId by BasketTable.merchantId
  var stockId by BasketTable.stockId
}