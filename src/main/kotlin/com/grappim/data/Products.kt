package com.grappim.data

import com.grappim.domain.ProductUnit
import com.grappim.models.Product
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.util.*

private const val productsTableName = "products_table"

object Products : LongIdTable(
    name = productsTableName
) {

    val barcode: Column<String> = varchar(
        name = "barcode",
        length = 50
    )

    val name: Column<String> = varchar(
        name = "name",
        length = 255
    )

    val stockId: Column<UUID> = (uuid(name = "stockId").references(
        ref = Stocks.id,
        onDelete = ReferenceOption.CASCADE
    )).uniqueIndex()

    val amount: Column<BigDecimal> = decimal(
        name = "amount",
        precision = 10,
        scale = 3
    )

    val unit: Column<ProductUnit> = enumeration(
        name = "unit",
        klass = ProductUnit::class
    )

    val merchantId: Column<UUID> = (uuid(name = "merchantId").references(
        ref = Users.id,
        onDelete = ReferenceOption.CASCADE
    )).uniqueIndex()

    val purchasePrice: Column<BigDecimal> = decimal(
        name = "purchasePrice",
        precision = 10,
        scale = 3
    )

    val sellingPrice: Column<BigDecimal> = decimal(
        name = "sellingPrice",
        precision = 10,
        scale = 3
    )

    val createdOn = datetime(
        name = "createdOn"
    )

    val updatedOn = datetime(
        name = "updatedOn"
    )

    val categoryId: Column<Long> = (long(name = "categoryId").references(
        ref = ProductCategories.id,
        onDelete = ReferenceOption.SET_NULL
    )).uniqueIndex()
}

class ProductEntity(
    id: EntityID<Long>
) : LongEntity(id) {
    companion object : LongEntityClass<ProductEntity>(Products)

    var barcode by Products.barcode
    var name by Products.name
    var stockId by Products.stockId
    var amount by Products.amount
    var unit by Products.unit
    var merchantId by Products.merchantId
    var purchasePrice by Products.purchasePrice
    var sellingPrice by Products.sellingPrice
    var createdOn by Products.createdOn
    var updatedOn by Products.updatedOn
    var categoryId by Products.categoryId

    fun toProduct(): Product = Product(
        id = this.id.value,
        barcode = this.barcode,
        name = this.name,
        stockId = this.stockId.toString(),
        amount = this.amount,
        unit = this.unit,
        merchantId = this.merchantId.toString(),
        purchasePrice = this.purchasePrice,
        sellingPrice = this.sellingPrice,
        createdOn = this.createdOn,
        updatedOn = this.updatedOn,
        categoryId = this.categoryId
    )
}