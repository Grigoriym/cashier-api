package com.grappim.config

import com.grappim.util.AuthenticationException
import com.grappim.util.UserDoesNotExists
import com.grappim.util.UserExists
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
            message = mapOf("errors" to mapOf("user" to listOf("exists")))
        )
    }
    exception<UserDoesNotExists> {
        call.respond(HttpStatusCode.NotFound)
    }
}