package com.grappim.routes

import com.grappim.data_service.model.order.CreateOrderRequestDTO
import com.grappim.data_service.model.order.CreateOrderResponseDTO
import com.grappim.domain.service.BasketService
import com.grappim.domain.service.OrderService
import com.grappim.mappers.toDomain
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.orderRouting() {

  val orderService by closestDI().instance<OrderService>()
  val basketService by closestDI().instance<BasketService>()

  route("/order") {
    authenticate {
      post("/create") {
        val request = call.receive<CreateOrderRequestDTO>()
        val createdOrderId = orderService.createOrder(
          request.order.toDomain()
        )
        basketService.removeBasket()
        call.respond(
          CreateOrderResponseDTO(
            id = createdOrderId
          )
        )
      }
    }
  }
}