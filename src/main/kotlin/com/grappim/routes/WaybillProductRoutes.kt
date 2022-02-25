package com.grappim.routes

import com.grappim.data_service.model.waybill.product.*
import com.grappim.domain.service.WaybillProductService
import com.grappim.mappers.toDTO
import com.grappim.mappers.toDomain
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.math.BigDecimal

fun Route.waybillProductRouting() {

  val waybillProductService by closestDI().instance<WaybillProductService>()

  route("/waybill/product") {
    authenticate {
      post("/filter") {
        val request = call.receive<FilterWaybillProductDTO>()
        val products = waybillProductService.filterProducts(
          request.toDomain()
        )
        if (products.isNotEmpty()) {
          val productsToSend = FilterWaybillProductsResponseDTO(
            products.toDTO()
          )
          call.respond(productsToSend)
        } else {
          call.respond(FilterWaybillProductsResponseDTO.empty())
        }
      }

      post {
        val request = call.receive<CreateWaybillProductRequestDTO>()
        val createdProduct = waybillProductService.createProduct(
          request.product.toDomain()
        ).toDTO()
        call.respond(
          CreateWaybillProductResponseDTO(
            totalCost = BigDecimal.ZERO,
            product = createdProduct
          )
        )
      }
      put {
        val updateProductDTO = call.receive<UpdateWaybillProductDTO>()
        val product = updateProductDTO.product.toDomain()
        val updatedProduct = waybillProductService.updateProduct(product).toDTO()
        call.respond(updatedProduct)
      }

      delete("/{id}") {
        val productId = call.parameters["id"]
          ?: return@delete call.respond(HttpStatusCode.BadRequest)

        waybillProductService.deleteProduct(productId.toLong())
        call.respondText(
          text = "Waybill product deleted",
          status = HttpStatusCode.OK
        )
      }
    }
  }

}