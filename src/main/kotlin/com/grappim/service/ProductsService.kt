package com.grappim.service

import com.grappim.data.ProductEntity
import com.grappim.data.Products
import com.grappim.domain.ProductUnit
import com.grappim.models.CreateProduct
import com.grappim.models.Product
import com.grappim.util.ProductNotFound
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*

class ProductsService {

    fun getProductById(id: Long): Product = transaction {
        ProductEntity.findById(id)?.toProduct() ?: throw ProductNotFound
    }

    fun getAllProductsByMerchantId(merchantId: String): List<Product> =
        transaction {
            val products = ProductEntity.find {
                (Products.merchantId eq UUID.fromString(merchantId))
            }

            products.map { it.toProduct() }
        }

    fun createProduct(
        createProduct: CreateProduct
    ): Long = transaction {
        val newProduct = ProductEntity.new {
            this.barcode = createProduct.barcode
            this.name = createProduct.name
            this.stockId = UUID.fromString(createProduct.stockId)
            this.amount = createProduct.amount
            this.unit = ProductUnit.getUnitFromString(createProduct.unit)
            this.merchantId = UUID.fromString(createProduct.merchantId)
            this.purchasePrice = createProduct.purchasePrice
            this.sellingPrice = createProduct.sellingPrice
            this.createdOn = LocalDateTime.parse(createProduct.createdOn)
            this.updatedOn = LocalDateTime.parse(createProduct.updatedOn)
        }
        return@transaction newProduct.id.value
    }
}