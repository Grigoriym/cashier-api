package com.grappim.db.tables

import com.grappim.common_domain.model.WaybillStatus
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.util.*

object WaybillTable : LongIdTable(
  name = "waybill_table"
) {

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val createdOn = datetime(
    name = "createdOn"
  )

  val updatedOn = datetime(
    name = "updatedOn"
  )

  val reservedTime = datetime(
    name = "reservedTime"
  ).nullable()

  val totalCost: Column<BigDecimal> = decimal(
    name = "totalCost",
    precision = 10,
    scale = 3
  ).default(BigDecimal.ZERO)

  val status: Column<WaybillStatus> = enumeration(
    name = "status",
    klass = WaybillStatus::class
  )

  val comment: Column<String> = varchar(
    name = "comment",
    length = 255
  ).default("")

  val number: Column<String> = varchar(
    name = "number",
    length = 255
  )
}