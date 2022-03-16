package com.grappim.data_service.service

import com.grappim.db.entities.BasketEntity
import com.grappim.db.entities.ProductEntity
import com.grappim.db.tables.BasketTable
import com.grappim.db.tables.ProductsTable
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.BasketService
import com.grappim.utils.toUUID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.math.BigDecimal

class BasketServiceImpl : BasketService, BaseService {

  override fun addProductToBasket(product: Product): Unit = transaction {
    val createdBasket = BasketEntity
      .find {
        (BasketTable.merchantId eq product.merchantId.toUUID()) and
            (BasketTable.stockId eq product.stockId.toUUID())
      }
      .firstOrNull() ?: BasketEntity.new {
      this.merchantId = product.merchantId.toUUID()
      this.stockId = product.stockId.toUUID()
    }

//    ProductsTable.update({ ProductsTable.id eq product.id }) { product ->
//      product[basket] = createdBasket.id
//    }
  }

  override fun subtractProduct(product: Product) {
    val foundProduct = ProductEntity.find { ProductsTable.id eq product.id }.firstOrNull()
    foundProduct?.let {
//      val count = it.
    }
  }

  override fun removeProductFromBasket(product: Product) {
    ProductsTable.deleteWhere { ProductsTable.id eq product.id }
  }

  override fun removeBasket(): Int = transaction {
    BasketTable.deleteAll()
  }

  private fun findBasket(product: Product) {

  }

}