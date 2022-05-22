package com.grappim.routes

import com.grappim.common_data.model.product_category.*
import com.grappim.domain.service.ProductCategoryService
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

fun Route.productCategoryRouting() {

  val productCategoryService by closestDI().instance<ProductCategoryService>()

  route("/category") {

    authenticate {
      post {
        val request = call.receive<CreateProductCategoryRequestDTO>()
        val newProductCategoryId = productCategoryService.createProductCategory(
          request.category.toDomain()
        )
        call.respond(
          CreateProductCategoryResponseDTO(
            id = newProductCategoryId
          )
        )
      }

      post("/filter") {
        val request = call.receive<FilterProductCategoriesRequestDTO>()
        val productCategories = productCategoryService.filterProductCategoriesByMerchantId(
          request.toDomain()
        )
        if (productCategories.isNotEmpty()) {
          val categoriesToSend = productCategories.toDTO()
          call.respond(FilterProductCategoriesResponseDTO(categoriesToSend))
        } else {
          call.respond(FilterProductCategoriesResponseDTO.empty())
        }
      }

      put {
        val updateProductCategoryDTO = call.receive<UpdateProductCategoryDTO>()
        val productCategory = updateProductCategoryDTO.category.toDomain()
        val updatedProductCategory = productCategoryService.updateCategory(
          productCategory = productCategory
        ).toDTO()
        call.respond(updatedProductCategory)
      }

      delete("/{id}") {
        val categoryId = call.parameters["id"]
          ?: return@delete call.respond(HttpStatusCode.BadRequest)
        productCategoryService.deleteProductCategoryById(categoryId.toLong())
        call.respondText(
          text = "Product Category deleted",
          status = HttpStatusCode.OK
        )
      }

    }
  }

}