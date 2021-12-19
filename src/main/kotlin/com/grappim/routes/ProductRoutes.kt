package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.models.CreateProductRequest
import com.grappim.models.CreateProductResponse
import com.grappim.service.ProductsService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.productRouting() {

    val productService by closestDI().instance<ProductsService>()

    route("/product") {
        authenticate {

            post {
                val request = call.receive<CreateProductRequest>()
                val newProductId = productService.createProduct(request.product)
                call.respond(
                    CreateProductResponse(
                        id = newProductId
                    )
                )
            }

            post("/filter") {
                val merchantId = getMerchantId()
                val products = productService.getAllProductsByMerchantId(
                    merchantId = merchantId
                )
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