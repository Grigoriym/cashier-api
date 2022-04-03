package com.grappim.routes

import com.grappim.common_data.model.cashbox.AddCashBoxDTO
import com.grappim.common_data.model.cashbox.CashBoxDTO
import com.grappim.common_data.model.cashbox.CashBoxResponseDTO
import com.grappim.common_data.model.cashbox.GetCashBoxesListDTO
import com.grappim.domain.service.CashBoxService
import com.grappim.mappers.toDTO
import com.grappim.mappers.toDomain
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.util.*

fun Route.cashBoxRouting() {

  val cashBoxService by closestDI().instance<CashBoxService>()

  route("/cashbox") {

    authenticate {
      post("/list") {
        val body = call.receive<GetCashBoxesListDTO>()
        val allCashBoxes = cashBoxService.getCashBoxes(
          body.toDomain()
        )
        val response = CashBoxResponseDTO(allCashBoxes.toDTO())
        if (allCashBoxes.isNotEmpty()) {
          call.respond(response)
        } else {
          call.respondText(
            text = "No cashboxes found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      get("/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText(
          text = "Missing or malformed id",
          status = HttpStatusCode.BadRequest
        )
        val cashBox: CashBoxDTO? = cashBoxService
          .getCashBoxById(UUID.fromString(id))
          ?.toDTO()
        if (cashBox == null) {
          call.respondText(
            text = "No cashBox with id: $id",
            status = HttpStatusCode.NotFound
          )
        } else {
          call.respond(cashBox)
        }
      }

      post {
        val cashBoxRequest = call.receive<AddCashBoxDTO>()
        val id = cashBoxService.createCashBox(cashBoxRequest.toDomain())
        call.respondText(
          text = "CashBox with id=$id stored correctly",
          status = HttpStatusCode.Accepted
        )
      }

      delete("/{id}") {
        val cashBoxId =
          call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        cashBoxService.deleteCashBox(cashBoxId)
        call.respond(HttpStatusCode.OK)
      }
    }
  }
}