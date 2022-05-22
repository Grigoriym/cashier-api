package com.grappim.config

import com.grappim.utils.*
import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun StatusPagesConfig.statusPages() {
  exception<AuthenticationException> { call, cause ->
    call.respond(HttpStatusCode.Unauthorized)
  }
  exception<UserExists> { call, cause ->
    call.respond(
      status = HttpStatusCode.UnprocessableEntity,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }
  exception<UserDoesNotExist> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<MerchantIdIsEmpty> { call, cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<StockIdIsEmpty> { call, cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<RegisterUserIncorrectFieldsException.BlankFieldsException> { call, cause ->
    call.respond(
      status = HttpStatusCode.Conflict,
      message = cause.exceptionMessage
    )
  }

  exception<ProductDoesNotExist> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<ProductCategoryDoesNotExist> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<ProductNameIsEmptyException> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<WaybillDoesNotExist> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<DuplicateProductNameException> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }

  exception<DuplicateProductBarcodeException> { call, cause ->
    call.respond(
      status = HttpStatusCode.NotFound,
      message = mapOf(
        "statusCode" to cause.statusCode,
        "message" to cause.message
      )
    )
  }
}