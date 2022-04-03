package com.grappim.routes

import com.grappim.authentication.jwt.JwtController
import com.grappim.authentication.jwt.getMerchantId
import com.grappim.common_data.model.user.*
import com.grappim.domain.service.AuthService
import com.grappim.mappers.toDomain
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

  route("/merch") {
    post {
      val registerUserDao = call.receive<RegisterUserDTO>()
      authService.register(registerUserDao.toDomain())
      call.respond(
        RegisterUserResponseDTO(
          phone = registerUserDao.phone,
          email = registerUserDao.email
        )
      )
    }

    post("/login") {
      val loginUserDao = call.receive<LoginUserDTO>()
      val user = authService.loginAndGetUser(loginUserDao.toDomain())
      val token = jwtController.getTokenForRespondAsString(user)

      call.respond(
        LoginUserResponseDTO(
          token = token,
          merchantId = user.id,
          merchantName = user.email
        )
      )
    }

    post("/login/guest") {
      authService.loginAsAGuest()
    }

    authenticate {
      get {
        val id = getMerchantId()
        val user = authService.getUserById(id)
        call.respond(user)
      }

      put {
        val updateUserDTO = call.receive<UpdateUserDTO>()
        val id = getMerchantId()
        val user = authService.updateUser(
          userId = id,
          updateUser = updateUserDTO.toDomain()
        )
        call.respond(user)
      }

      delete("/{id}") {
        val deleteUserId =
          call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        authService.deleteUserById(deleteUserId)
        call.respondText(
          text = "User deleted",
          status = HttpStatusCode.OK
        )
      }
    }
  }
}