package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.common_data.model.basket.AddBasketProductDTO
import com.grappim.common_data.model.basket.BasketProductDTO
import com.grappim.common_data.model.basket.SearchProductsRequestDTO
import com.grappim.common_data.model.basket.SubtractBasketProductResponseDTO
import com.grappim.common_data.model.products.ProductDTO
import com.grappim.domain.service.BasketService
import com.grappim.mappers.toDTO
import com.grappim.mappers.toDomain
import com.grappim.utils.toUUID
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.basketRouting() {
  val basketService by closestDI().instance<BasketService>()

  route("/basket") {
    authenticate {
      get {
        val merchantId = getMerchantId()
        val stockId = call.parameters["stockId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val products = basketService.getBasketProducts(
          merchantId = merchantId.toUUID(),
          stockId = stockId.toUUID()
        ).map {
          it.toDTO()
        }
        call.respond(products)
      }
      post("/add") {
        val productToAdd = call.receive<AddBasketProductDTO>()
        val product = productToAdd.toDomain()
        val basketProduct = basketService.addProductToBasket(product)
        call.respond(basketProduct.toDTO())
      }
      post("/subtract") {
        val productToSubtract = call.receive<BasketProductDTO>()
        val product = productToSubtract.toDomain()
        val basketProduct = basketService.subtractProduct(product)
        call.respond(
          SubtractBasketProductResponseDTO(
            basketProduct = basketProduct.basketProduct?.toDTO(),
            isRemoved = basketProduct.isRemoved
          )
        )
      }
      post("/remove") {
        val productToRemove = call.receive<ProductDTO>()
        val product = productToRemove.toDomain()
        basketService.removeProductFromBasket(product)
        call.respondText(
          text = "Product remove",
          status = HttpStatusCode.OK
        )
      }
      delete("/clear") {
        basketService.removeBasket()
        call.respondText(
          text = "Basket cleared",
          status = HttpStatusCode.OK
        )
      }
      post("/search") {
        val merchantId = getMerchantId()
        val request = call.receive<SearchProductsRequestDTO>()
        val products = basketService.searchProducts(
          merchantId = merchantId.toUUID(),
          stockId = request.stockId,
          searchQuery = request.searchQuery
        )
        call.respond(products.map { it.toDTO() })
      }
    }
  }
}