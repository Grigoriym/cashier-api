package com.grappim.routes

import com.grappim.common_data.model.waybill.product.*
import com.grappim.domain.service.WaybillProductService
import com.grappim.mappers.toDTO
import com.grappim.mappers.toDomain
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

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
      post("/get") {
        val request = call.receive<GetWaybillByBarcodeRequestDTO>()
        val domain = request.toDomain()
        val foundProduct = waybillProductService.getProduct(domain).toDTO()
        call.respond(GetWaybillProductResponseDTO(foundProduct))
      }

      post {
        val request = call.receive<CreateWaybillProductRequestDTO>()
        val createdProduct = waybillProductService.createProduct(
          request.product.toDomain()
        )
        call.respond(
          CreateWaybillProductResponseDTO(
            createdProduct
          )
        )
      }
      put {
        val updateProductDTO = call.receive<UpdateWaybillProductDTO>()
        val product = updateProductDTO.product.toDomain()
        val updatedProduct = waybillProductService.updateProduct(product)
        call.respond(UpdateWaybillProductResponseDTO(updatedProduct))
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