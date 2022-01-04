package com.grappim.config

import com.grappim.utils.AuthenticationException
import com.grappim.utils.ProductNotFound
import com.grappim.utils.RegisterUserIncorrectFieldsException
import com.grappim.utils.UserDoesNotExists
import com.grappim.utils.UserExists
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
    exception<UserDoesNotExists> { cause ->
        call.respond(
            status = HttpStatusCode.NotFound,
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