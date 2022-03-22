package com.grappim.data_service.service

import com.grappim.db.entities.BasketEntity
import com.grappim.db.entities.BasketProductEntity
import com.grappim.db.mappers.toDomain
import com.grappim.db.tables.BasketProductTable
import com.grappim.db.tables.BasketTable
import com.grappim.domain.model.basket.AddBasketProduct
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.domain.model.basket.SubtractBasketProduct
import com.grappim.domain.model.product.Product
import com.grappim.domain.service.BasketService
import com.grappim.utils.bigDecimalOne
import com.grappim.utils.bigDecimalZero
import com.grappim.utils.isLessThanOrEquals
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class BasketServiceImpl : BasketService, BaseService {

  override fun addProductToBasket(
    newProduct: AddBasketProduct
  ): BasketProduct = transaction {
    val currentBasket = getCurrentBasket(newProduct.merchantId, newProduct.stockId)
    val alreadySavedBasketProduct = BasketProductEntity.find {
      BasketProductTable.productId eq newProduct.productId
    }.firstOrNull()
    if (alreadySavedBasketProduct == null) {
      BasketProductTable.insertAndGetId { product ->
        product[amount] = bigDecimalOne()
        product[barcode] = newProduct.barcode
        product[name] = newProduct.name
        product[productId] = newProduct.productId
        product[stockId] = newProduct.stockId
        product[merchantId] = newProduct.merchantId
        product[unit] = newProduct.unit
        product[sellingPrice] = newProduct.sellingPrice
        product[basket] = currentBasket.id
      }
    } else {
      BasketProductTable.update({
        BasketProductTable.productId eq newProduct.productId
      }
      ) { product ->
        product[amount] = amount.plus(bigDecimalOne())
      }
    }
    val basketProduct = BasketProductEntity.find {
      BasketProductTable.productId eq newProduct.productId
    }.firstOrNull() ?: error("lol")
    return@transaction basketProduct.toDomain()
  }

  private fun getCurrentBasket(
    merchantId: UUID,
    stockId: UUID
  ): BasketEntity = transaction {
    BasketEntity
      .find {
        (BasketTable.merchantId eq merchantId) and
            (BasketTable.stockId eq stockId)
      }.firstOrNull() ?: BasketEntity.new {
      this.merchantId = merchantId
      this.stockId = stockId
    }
  }

  override fun subtractProduct(
    product: BasketProduct
  ): SubtractBasketProduct = transaction {
    val foundBasketProduct = BasketProductEntity.find {
      BasketProductTable.productId eq product.productId
    }.firstOrNull() ?: error("lol")
    val newAmount = foundBasketProduct.amount.minus(bigDecimalOne())
    if (newAmount.isLessThanOrEquals(bigDecimalZero())) {
      BasketProductTable.deleteWhere {
        BasketProductTable.id eq foundBasketProduct.id
      }
      return@transaction SubtractBasketProduct(
        basketProduct = foundBasketProduct.toDomain(),
        isRemoved = true
      )
    } else {
      BasketProductTable.update(
        { BasketProductTable.id eq foundBasketProduct.id }
      ) { basketProduct ->
        basketProduct[amount] = newAmount
      }
      val newBasketProduct = BasketProductEntity.find {
        BasketProductTable.id eq foundBasketProduct.id
      }.first()
      return@transaction SubtractBasketProduct(
        basketProduct = newBasketProduct.toDomain(),
        isRemoved = false
      )
    }
  }

  override fun removeProductFromBasket(product: Product) {
    BasketProductTable.deleteWhere {
      BasketProductTable.productId eq product.id
    }
  }

  override fun getBasketProducts(
    merchantId: UUID,
    stockId: UUID
  ): List<BasketProduct> = transaction {
    val basket = BasketEntity.find {
      (BasketTable.merchantId eq merchantId) and
          (BasketTable.stockId eq stockId)
    }.firstOrNull()
    basket?.basketItems?.map { it.toDomain() } ?: emptyList()
  }

  override fun removeBasket(): Int = transaction {
    BasketTable.deleteAll()
  }

  override fun searchProducts(
    merchantId: UUID,
    stockId: UUID,
    searchQuery: String
  ): List<BasketProduct> = transaction {
    BasketProductEntity.find {
      (BasketProductTable.merchantId eq merchantId) and
          (BasketProductTable.stockId eq stockId) and
          (BasketProductTable.name like searchQuery)
    }.toList().map { it.toDomain() }
  }

}