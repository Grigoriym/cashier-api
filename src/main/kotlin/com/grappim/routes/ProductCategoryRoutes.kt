package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.domain.service.ProductCategoryService
import com.grappim.mappers.toCreateProductCategory
import com.grappim.model.CreateProductCategoryRequestDTO
import com.grappim.model.CreateProductCategoryResponseDTO
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.productCategoryRouting() {

  val productCategoryService by closestDI().instance<ProductCategoryService>()

  route("/category") {
    authenticate {

      post {
        val request = call.receive<CreateProductCategoryRequestDTO>()
        val newProductCategoryId = productCategoryService.createProductCategory(
          request.category.toCreateProductCategory()
        )
        call.respond(
          CreateProductCategoryResponseDTO(
            id = newProductCategoryId
          )
        )
      }

      post("/filter") {
        val merchantId = getMerchantId()
        val productCategories = productCategoryService.getAllProductCategoriesByMerchantId(
          merchantId = merchantId
        )
        if (productCategories.isNotEmpty()) {
          call.respond(productCategories)
        } else {
          call.respondText(
            text = "No product categories found",
            status = HttpStatusCode.NotFound
          )
        }
      }

    }
  }

}