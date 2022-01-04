package com.grappim.db.service

import com.grappim.db.entities.CashBoxEntity
import com.grappim.db.mappers.toCashBox
import com.grappim.db.tables.CashBoxesTable
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.domain.model.cashbox.GetCashBoxesList
import com.grappim.domain.service.CashBoxService
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CashBoxServiceImpl : CashBoxService {

  override fun getCashBoxById(uuid: UUID): CashBox? = transaction {
    CashBoxEntity.findById(uuid)?.toCashBox()
  }

//  fun getAllCashBoxes(): List<CashBoxDTO> = transaction {
//    CashBoxEntity.all().map(CashBoxEntity::toCashBoxDTO)
//  }

  override fun getCashBoxes(
    getCashBoxesList: GetCashBoxesList
  ): List<CashBox> = transaction {
    CashBoxEntity.find {
      CashBoxesTable.merchantId eq UUID.fromString(getCashBoxesList.merchantId) and
          (CashBoxesTable.stockId eq UUID.fromString(getCashBoxesList.stockId))
    }.map { it.toCashBox() }
  }

//    fun addCashBox(cashBoxToAdd: CashBoxToAdd) = transaction {
//        CashBoxEntity.new {
//            this.merchantId = cashBoxToAdd.merchantId
//            this.name = cashBoxToAdd.name
//            this.stockId = cashBoxToAdd.stockId
//        }
//    }

//  fun updateCashBox(cashBox: CashBoxDTO) = transaction {
//
//  }

  override fun deleteCashBox(cashBoxId: String) = transaction {
    CashBoxEntity[UUID.fromString(cashBoxId)].delete()
  }
}