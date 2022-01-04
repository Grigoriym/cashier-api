package com.grappim.db.entities

import com.grappim.db.tables.CashBoxesTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class CashBoxEntity(
  id: EntityID<UUID>
) : UUIDEntity(id) {

  companion object : UUIDEntityClass<CashBoxEntity>(CashBoxesTable)

  var name by CashBoxesTable.name
  var merchantId by CashBoxesTable.merchantId
  var stockId by CashBoxesTable.stockId

}