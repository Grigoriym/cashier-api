package com.grappim.routes

import com.grappim.common_data.model.base.BaseFilterDTO
import com.grappim.common_data.model.products.CreateProductRequestDTO
import com.grappim.common_data.model.products.CreateProductResponseDTO
import com.grappim.common_data.model.products.FilterProductsResponseDTO
import com.grappim.common_data.model.products.UpdateProductDTO
import com.grappim.domain.service.ProductService
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

fun Route.productRouting() {

  val productService by closestDI().instance<ProductService>()

  route("/product") {
    authenticate {
      post {
        val request = call.receive<CreateProductRequestDTO>()
        val createdProduct = productService.createProduct(
          request.product.toDomain()
        )
        call.respond(
          CreateProductResponseDTO(
            product = createdProduct.toDTO()
          )
        )
      }

      post("/filter") {
        val request = call.receive<BaseFilterDTO>()
        val products = productService.filterProducts(
          request.toDomain()
        )
        if (products.isNotEmpty()) {
          val productsToSend = FilterProductsResponseDTO(products.toDTO())
          call.respond(productsToSend)
        } else {
          call.respond(FilterProductsResponseDTO.empty())
        }
      }

      put {
        val updateProductDTO = call.receive<UpdateProductDTO>()
        val product = updateProductDTO.product.toDomain()
        val updatedProduct = productService.updateProduct(product).toDTO()
        call.respond(updatedProduct)
      }

      delete("/{id}") {
        val productId = call.parameters["id"]
          ?: return@delete call.respond(HttpStatusCode.BadRequest)
        productService.deleteProduct(productId.toLong())
        call.respondText(
          text = "Product deleted",
          status = HttpStatusCode.OK
        )
      }

    }
  }
}