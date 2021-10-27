package com.grappim.service

import com.grappim.data.CashBoxEntity
import com.grappim.data.CashBoxes
import com.grappim.models.CashBox
import com.grappim.models.CashBoxToAdd
import com.grappim.models.GetCashBoxesList
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CashBoxService {

    fun getCashBoxById(uuid: UUID): CashBox? = transaction {
        CashBoxEntity.findById(uuid)?.toCashBox()
    }

    fun getAllCashBoxes(): List<CashBox> = transaction {
        CashBoxEntity.all().map(CashBoxEntity::toCashBox)
    }

    fun getCashBoxes(
        getCashBoxesList: GetCashBoxesList
    ): List<CashBox> = transaction {
        CashBoxEntity.find {
            CashBoxes.merchantId eq UUID.fromString(getCashBoxesList.merchantId) and
                    (CashBoxes.stockId eq UUID.fromString(getCashBoxesList.stockId))
        }.map { it.toCashBox() }
    }

//    fun addCashBox(cashBoxToAdd: CashBoxToAdd) = transaction {
//        CashBoxEntity.new {
//            this.merchantId = cashBoxToAdd.merchantId
//            this.name = cashBoxToAdd.name
//            this.stockId = cashBoxToAdd.stockId
//        }
//    }

    fun updateCashBox(cashBox: CashBox) = transaction {

    }

    fun deleteCashBox(cashBoxId: String) = transaction {
        CashBoxEntity[UUID.fromString(cashBoxId)].delete()
    }
}