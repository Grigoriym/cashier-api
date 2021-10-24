package com.grappim.data

import com.grappim.models.CashBox
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

private const val cashBoxesTableName = "cashboxes_table"

object CashBoxes : UUIDTable(
    name = cashBoxesTableName,
) {

    val name: Column<String> = varchar(
        name = "name",
        length = 50
    )

    val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
        ref = Users.id,
        onDelete = ReferenceOption.CASCADE
    )).uniqueIndex()

    val stockId: Column<UUID> = (uuid(name = "stockId").references(
        ref = Stocks.id,
        onDelete = ReferenceOption.CASCADE
    )).uniqueIndex()

}

class CashBoxEntity(
    id: EntityID<UUID>
) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CashBoxEntity>(CashBoxes)

    var name by CashBoxes.name
    var merchantId by CashBoxes.merchantId
    var stockId by CashBoxes.stockId

    fun toCashBox(): CashBox = CashBox(
        name = name,
        cashBoxId = id.value.toString(),
        merchantId = merchantId.toString(),
        stockId = stockId.toString()
    )
}