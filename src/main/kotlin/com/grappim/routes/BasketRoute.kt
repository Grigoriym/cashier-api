package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.data_service.model.basket.AddBasketProductDTO
import com.grappim.data_service.model.basket.BasketProductDTO
import com.grappim.data_service.model.basket.SearchProductsRequestDTO
import com.grappim.data_service.model.basket.SubtractBasketProductResponseDTO
import com.grappim.data_service.model.products.ProductDTO
import com.grappim.domain.service.BasketService
import com.grappim.mappers.toDTO
import com.grappim.mappers.toDomain
import com.grappim.utils.toUUID
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
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