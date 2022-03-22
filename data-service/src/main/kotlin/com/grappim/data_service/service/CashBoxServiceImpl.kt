package com.grappim.data_service.service

import com.grappim.db.entities.CashBoxEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.CashBoxesTable
import com.grappim.domain.model.cashbox.AddCashBox
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList
import com.grappim.domain.model.stock.Stock
import com.grappim.domain.service.CashBoxService
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CashBoxServiceImpl : CashBoxService {

  override fun getCashBoxById(uuid: UUID): CashBox? = transaction {
    CashBoxEntity.findById(uuid)?.toDomain()
  }

  override fun getCashBoxes(
    getCashBoxesList: GetCashBoxesList
  ): List<CashBox> = transaction {
    CashBoxEntity.find {
      (CashBoxesTable.merchantId eq getCashBoxesList.merchantId) and
          (CashBoxesTable.stockId eq getCashBoxesList.stockId)
    }.map { it.toDomain() }
  }

  override fun deleteCashBox(cashBoxId: String) = transaction {
    CashBoxEntity[UUID.fromString(cashBoxId)].delete()
  }

  override fun createCashBox(addCashBox: AddCashBox): String =
    transaction {
      val newCashBox = CashBoxEntity.new {
        this.merchantId = addCashBox.merchantId
        this.name = addCashBox.name
        this.stockId = addCashBox.stockId
      }
      return@transaction newCashBox.id.value.toString()
    }

  override fun createCashBox(merchantId: UUID, stock: Stock) = transaction {
    CashBoxesTable.insertAndGetId {
      it[name] = "${stock.name} cashBox"
      it[this.merchantId] = merchantId
      it[stockId] = stock.id
    }.value
  }
}