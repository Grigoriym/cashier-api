package com.grappim.data

import com.grappim.models.Stock
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

private const val stocksTableName = "stocks_table"

object Stocks : UUIDTable(
    name = stocksTableName
) {

    val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
        ref = Users.id,
        onDelete = ReferenceOption.CASCADE
    )).uniqueIndex()

    val stockName: Column<String> = varchar(
        name = "stockName",
        length = 50
    )

}

class StockEntity(
    id: EntityID<UUID>
) : UUIDEntity(id) {
    companion object : UUIDEntityClass<StockEntity>(Stocks)

    var merchantId by Stocks.merchantId
    var stockName by Stocks.stockName

    fun toStock(): Stock = Stock(
        id = id.value.toString(),
        merchantId = merchantId.toString(),
        name = stockName
    )
}