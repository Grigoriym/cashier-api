package com.grappim.routes

import com.grappim.models.CashBox
import com.grappim.models.CashBoxToAdd
import com.grappim.service.CashBoxService
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

    route("cashbox") {
        authenticate {
            get {
                val allCashBoxes = cashBoxService.getAllCashBoxes()
                if (allCashBoxes.isEmpty()) {
                    call.respond(allCashBoxes)
                } else {
                    call.respondText(
                        text = "No cashboxes found",
                        status = HttpStatusCode.NotFound
                    )
                }
            }

            get("{id}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    text = "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val cashBox: CashBox? = cashBoxService.getCashBoxById(UUID.fromString(id))
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
                val cashBoxRequest = call.receive<CashBoxToAdd>()
                cashBoxService.addCashBox(cashBoxRequest)
                call.respondText(
                    text = "CashBox stored correctly",
                    status = HttpStatusCode.Accepted
                )
            }

            delete("/{id}") {
                val cashBoxId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                cashBoxService.deleteCashBox(cashBoxId)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}