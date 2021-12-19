package com.grappim.data

import com.grappim.models.ProductCategory
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

private const val productCategoriesTableName = "product_category_table"

object ProductCategories : LongIdTable(
    name = productCategoriesTableName
) {
    val name: Column<String> = varchar(
        name = "name",
        length = 100
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

class ProductCategoryEntity(
    id: EntityID<Long>
) : LongEntity(id) {
    companion object : LongEntityClass<ProductCategoryEntity>(ProductCategories)

    var name by ProductCategories.name
    var merchantId by ProductCategories.merchantId
    var stockId by ProductCategories.stockId

    fun toProductCategory(): ProductCategory =
        ProductCategory(
            id = this.id.value,
            name = this.name,
            merchantId = this.merchantId.toString(),
            stockId = this.stockId.toString()
        )
}