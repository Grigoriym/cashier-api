package com.grappim.service

import com.grappim.data.ProductEntity
import com.grappim.data.Products
import com.grappim.models.Product
import com.grappim.util.ProductNotFound
import org.jetbrains.exposed.sql.transactions.transaction
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
}