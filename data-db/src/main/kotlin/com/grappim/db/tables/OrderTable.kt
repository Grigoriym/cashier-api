package com.grappim.db.tables

import com.grappim.domain.model.order.PayType
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.util.*

object OrderTable : LongIdTable(
  name = "orders_table"
) {

  val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
    ref = UsersTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val stockId: Column<UUID> = (uuid(name = "stockId").references(
    ref = StocksTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val cashBoxId: Column<UUID> = (uuid(name = "cashBoxId").references(
    ref = CashBoxesTable.id,
    onDelete = ReferenceOption.CASCADE
  ))

  val timestamp = datetime(
    name = "timestamp"
  )

  val totalSum: Column<BigDecimal> = decimal(
    name = "totalSum",
    precision = 10,
    scale = 3
  )

  val payType: Column<PayType> = enumeration(
    name = "payType",
    klass = PayType::class
  )

  val orderItems = reference(
    name = "orderItems",
    foreign = OrderItemTable,
    onDelete = ReferenceOption.CASCADE
  )

}