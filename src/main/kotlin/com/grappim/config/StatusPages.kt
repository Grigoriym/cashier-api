package com.grappim.config

import com.grappim.utils.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*

fun StatusPages.Configuration.statusPages() {
  exception<AuthenticationException> {
    call.respond(HttpStatusCode.Unauthorized)
  }
  exception<UserExists> { cause ->
    call.respond(
      status = HttpStatusCode.UnprocessableEntity,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }
  exception<UserDoesNotExist> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<MerchantIdIsEmpty> { cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<StockIdIsEmpty> { cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<RegisterUserIncorrectFieldsException.BlankFieldsException> { cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = cause.exceptionMessage
    )
  }

  exception<ProductDoesNotExist> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<ProductCategoryDoesNotExist> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<ProductNameIsEmptyException> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<WaybillDoesNotExist> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<DuplicateProductNameException> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<DuplicateProductBarcodeException> { cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }
}