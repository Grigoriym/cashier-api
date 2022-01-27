package com.grappim.routes

import com.grappim.data_service.model.products.*
import com.grappim.domain.service.ProductService
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
        val request = call.receive<FilterProductsRequestDTO>()
        val products = productService.filterProducts(
          request.toDomain()
        )
        if (products.isNotEmpty()) {
          val productsToSend = FilterProductsResponseDTO(products.map {
            it.toDTO()
          })
          call.respond(productsToSend)
        } else {
          call.respondText(
            text = "No products found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      put {
        val updateProductDTO = call.receive<UpdateProductDTO>()
        val product = updateProductDTO.product.toDomain()
        val updatedProduct = productService.updateProduct(product).toDTO()
        call.respond(updatedProduct)
      }

    }
  }
}