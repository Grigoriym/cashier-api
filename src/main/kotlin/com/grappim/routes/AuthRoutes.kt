package com.grappim.routes

import com.grappim.authentication.jwt.JwtController
import com.grappim.authentication.jwt.getId
import com.grappim.models.*
import com.grappim.service.AuthService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.authRouting() {

    val authService by closestDI().instance<AuthService>()
    val jwtController by closestDI().instance<JwtController>()

    route("/user") {

        post {
            val registerUser = call.receive<RegisterUser>()
            authService.register(registerUser)
            call.respond(
                RegisterUserResponse(
                    phone = registerUser.phone,
                    email = registerUser.email
                )
            )
        }

        post("/login") {
            val loginUser = call.receive<LoginUser>()
            val user = authService.loginAndGetUser(loginUser)
            val token = jwtController.getTokenForRespondAsString(user)

            call.respond(
                LoginUserResponse(
                    token = token,
                    merchantId = user.id,
                    merchantName = user.email
                )
            )
        }

        authenticate {
            get {
                val id = getId()
                val user = authService.getUserById(id)
                call.respond(user)
            }

            put {
                val updateUser = call.receive<UpdateUser>()
                val id = getId()
                val user = authService.updateUser(
                    userId = id,
                    updateUser = updateUser
                )
                call.respond(user)
            }

            delete("/{id}") {
                val deleteUserId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                authService.deleteUserById(deleteUserId)
                call.respondText(
                    text = "User deleted",
                    status = HttpStatusCode.OK
                )
            }
        }
    }
}