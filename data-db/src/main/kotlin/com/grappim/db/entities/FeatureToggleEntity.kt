package com.grappim.db.entities

import com.grappim.db.tables.FeatureToggleTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class FeatureToggleEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<FeatureToggleEntity>(FeatureToggleTable)

  var merchantId by FeatureToggleTable.merchantId
  var stockId by FeatureToggleTable.stockId
  var isWaybillEnabled by FeatureToggleTable.isWaybillEnabled
  var isSalesEnabled by FeatureToggleTable.isSalesEnabled
}