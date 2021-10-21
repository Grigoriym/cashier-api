package com.grappim.data

import com.grappim.models.CashBox
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

private const val cashBoxesTableName = "cashboxes_table"

object CashBoxes : UUIDTable(
    name = cashBoxesTableName,
    columnName = "cashBoxId"
) {

    val name: Column<String> = varchar(
        name = "name",
        length = 50
    )

    val merchantId: Column<String> = varchar(
        name = "merchantId",
        length = 50
    )

    val stockId: Column<String> =
        varchar(
            name = "stockId",
            length = 50
        )

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
        merchantId = merchantId,
        stockId = stockId
    )
}