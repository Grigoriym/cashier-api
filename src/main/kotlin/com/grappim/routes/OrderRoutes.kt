package com.grappim.routes

import com.grappim.data_service.model.order.CreateOrderRequestDTO
import com.grappim.data_service.model.order.CreateOrderResponseDTO
import com.grappim.domain.service.BasketService
import com.grappim.domain.service.OrderService
import com.grappim.mappers.toDomain
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
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