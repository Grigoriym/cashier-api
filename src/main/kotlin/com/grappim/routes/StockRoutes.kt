package com.grappim.routes

import com.grappim.models.Stock
import com.grappim.models.StockToCreate
import com.grappim.service.StockService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.util.*

fun Route.stockRouting() {

    val stockService by closestDI().instance<StockService>()

    route("stock") {
        authenticate {
            get {
                val allStocks = stockService.getAllStocks()
                if (allStocks.isEmpty()) {
                    call.respond(allStocks)
                } else {
                    call.respondText(
                        text = "No stocks found",
                        status = HttpStatusCode.NotFound
                    )
                }
            }

            get("{id}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    text = "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val stock: Stock? = stockService.getStockById(UUID.fromString(id))
                if (stock == null) {
                    call.respondText(
                        text = "No stock with id: $id",
                        status = HttpStatusCode.NotFound
                    )
                } else {
                    call.respond(stock)
                }
            }

            post {
                val stockRequest = call.receive<StockToCreate>()
                stockService.addStock(stockRequest)
                call.respondText(
                    text = "Stock stored correctly",
                    status = HttpStatusCode.Accepted
                )
            }

            delete("/{id}") {
                val stockId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                stockService.deleteStock(stockId)
                call.respond(HttpStatusCode.OK)
            }
        }

    }
}