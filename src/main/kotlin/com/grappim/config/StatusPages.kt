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
    exception<UserExists> {
        call.respond(
            status = HttpStatusCode.UnprocessableEntity,
            message = mapOf(
                "errors" to mapOf(
                    "user" to listOf("exists")
                )
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

    exception<MerchantIdIsEmpty> { cause->
        call.respond(
            status = HttpStatusCode.Conflict,
            message = mapOf(
                "statusCode" to cause.statusCode,
                "message" to cause.message
            )
        )
    }

    exception<StockIdIsEmpty> { cause->
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

    exception<ProductNotFound> {
        call.respond(HttpStatusCode.NotFound)
    }
}