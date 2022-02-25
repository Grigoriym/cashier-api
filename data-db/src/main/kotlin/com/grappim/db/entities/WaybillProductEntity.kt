package com.grappim.db.entities

import com.grappim.db.tables.WaybillProductTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WaybillProductEntity(
  id: EntityID<Long>
) : LongEntity(id) {

  companion object : LongEntityClass<WaybillProductEntity>(WaybillProductTable)

  var name by WaybillProductTable.name
  var barcode by WaybillProductTable.barcode
  var purchasePrice by WaybillProductTable.purchasePrice
  var sellingPrice by WaybillProductTable.sellingPrice
  var amount by WaybillProductTable.amount
  var waybillId by WaybillProductTable.waybillId
  var productId by WaybillProductTable.productId
  var createdOn by WaybillProductTable.createdOn
  var updatedOn by WaybillProductTable.updatedOn

}