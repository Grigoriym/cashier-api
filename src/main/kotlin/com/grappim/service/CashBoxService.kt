package com.grappim.service

import com.grappim.data.CashBoxEntity
import com.grappim.models.CashBox
import com.grappim.models.CashBoxToAdd
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CashBoxService {

    fun getCashBoxById(uuid: UUID): CashBox? = transaction {
        CashBoxEntity.findById(uuid)?.toCashBox()
    }

    fun getAllCashBoxes(): List<CashBox> = transaction {
        CashBoxEntity.all().map(CashBoxEntity::toCashBox)
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