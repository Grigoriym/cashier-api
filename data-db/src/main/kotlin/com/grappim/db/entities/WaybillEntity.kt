package com.grappim.db.entities

import com.grappim.db.tables.WaybillTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WaybillEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<WaybillEntity>(WaybillTable)

  var merchantId by WaybillTable.merchantId
  var stockId by WaybillTable.stockId
  var createdOn by WaybillTable.createdOn
  var updatedOn by WaybillTable.updatedOn
  var totalCost by WaybillTable.totalCost
  var status by WaybillTable.status
  var comment by WaybillTable.comment
  var reservedTime by WaybillTable.reservedTime
  var number by WaybillTable.number

}