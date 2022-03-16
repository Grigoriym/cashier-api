package com.grappim.routes

import com.grappim.data_service.model.products.ProductDTO
import com.grappim.domain.service.BasketService
import com.grappim.mappers.toDomain
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
      post("/add") {
        val productToAdd = call.receive<ProductDTO>()
        val product = productToAdd.toDomain()
        basketService.addProductToBasket(product)
        call.respondText(
          text = "Product added",
          status = HttpStatusCode.OK
        )
      }
      post("/subtract"){
        val productToSubtract = call.receive<ProductDTO>()
        val product = productToSubtract.toDomain()
      }
      post("/remove") {
        val productToRemove = call.receive<ProductDTO>()
        val product = productToRemove.toDomain()
      }
      delete("/clear") {
        basketService.removeBasket()
        call.respondText(
          text = "Basket cleared",
          status = HttpStatusCode.OK
        )
      }
    }
  }
}