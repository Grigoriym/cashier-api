package com.grappim.routes

import com.grappim.authentication.jwt.JwtController
import com.grappim.models.LoginUser
import com.grappim.models.RegisterUser
import com.grappim.models.UpdateUser
import com.grappim.service.AuthService
import com.grappim.util.AuthenticationException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.io.File

fun Route.authRouting() {

    val authService by closestDI().instance<AuthService>()
    val jwtController by closestDI().instance<JwtController>()

    route("/user") {

        post {
            val registerUser = call.receive<RegisterUser>()
            authService.register(registerUser)
            call.respondText(
                text = "User stored correctly",
                status = HttpStatusCode.Accepted
            )
        }

        post("/login") {
            val loginUser = call.receive<LoginUser>()
            val user = authService.loginAndGetUser(loginUser)
            val token = jwtController.generateToken(loginUser)

            call.respond(hashMapOf("token" to token))
        }

        authenticate {
            get {
                val email = getEmail()
                val user = authService.getUserByEmail(email)
                call.respond(user)
            }
        }
    }
}

private fun PipelineContext<*, ApplicationCall>.getEmail(): String =
    call.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("email")
        ?.asString() ?: throw IllegalArgumentException("lol")