package com.grappim.data

import com.grappim.models.Stock
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

private const val stocksTableName = "stocks_table"

object Stocks : UUIDTable(
    name = stocksTableName,
    columnName = "stockId"
) {

    val merchantId: Column<String> = varchar(
        name = "merchantId",
        length = 50
    )

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
        stockId = id.value.toString(),
        merchantId = merchantId,
        stockName = stockName
    )
}