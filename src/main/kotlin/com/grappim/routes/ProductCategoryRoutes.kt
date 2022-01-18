package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.data_service.model.product_category.CreateProductCategoryRequestDTO
import com.grappim.data_service.model.product_category.CreateProductCategoryResponseDTO
import com.grappim.data_service.model.product_category.FilterProductCategoriesRequestDTO
import com.grappim.data_service.model.product_category.UpdateProductCategoryDTO
import com.grappim.domain.service.ProductCategoryService
import com.grappim.mappers.toDomain
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
          call.respond(productCategories)
        } else {
          call.respondText(
            text = "No product categories found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      put {
        val updateProductCategoryDTO = call.receive<UpdateProductCategoryDTO>()
        val id = getMerchantId()
        val productCategory = updateProductCategoryDTO.productCategory.toDomain()
        val updatedProductCategory = productCategoryService.updateUser(
          merchantId = id,
          productCategory = productCategory
        )
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