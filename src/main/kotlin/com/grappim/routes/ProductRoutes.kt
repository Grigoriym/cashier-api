package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.domain.service.ProductService
import com.grappim.mappers.toCreateProduct
import com.grappim.model.CreateProductRequestDTO
import com.grappim.model.CreateProductResponseDTO
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
        val newProductId = productService.createProduct(
          request.product.toCreateProduct()
        )
        call.respond(
          CreateProductResponseDTO(
            id = newProductId
          )
        )
      }

      post("/filter") {
        val merchantId = getMerchantId()
        val products = productService.getAllProductsByMerchantId(
          merchantId = merchantId
        ).map {

        }
        if (products.isNotEmpty()) {
          call.respond(products)
        } else {
          call.respondText(
            text = "No products found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      put {

      }

    }
  }
}