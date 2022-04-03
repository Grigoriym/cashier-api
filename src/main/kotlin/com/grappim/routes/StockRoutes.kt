package com.grappim.routes

import com.grappim.common_data.model.stock.StockDTO
import com.grappim.common_data.model.stock.StocksResponseDTO
import com.grappim.domain.service.StockService
import com.grappim.mappers.toDTO
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import java.util.*

fun Route.stockRouting() {

  val stockService by closestDI().instance<StockService>()

  route("/stocks") {
    authenticate {
      get {
        val allStocks = stockService.getAllStocks()
        val response = StocksResponseDTO(allStocks.toDTO())
        if (allStocks.isNotEmpty()) {
          call.respond(response)
        } else {
          call.respondText(
            text = "No stocks found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      get("/list/{merchantId}") {
        val merchantId = call.parameters["merchantId"] ?: return@get call.respondText(
          text = "Missing or malformed merchantId",
          status = HttpStatusCode.BadRequest
        )
        val stocks = stockService.getStocksByMerchantId(merchantId)
        val response = StocksResponseDTO(stocks.toDTO())
        if (stocks.isNotEmpty()) {
          call.respond(response)
        } else {
          call.respondText(
            text = "No stocks for merchantId: $merchantId found",
            status = HttpStatusCode.NotFound
          )
        }
      }

      get("/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText(
          text = "Missing or malformed id",
          status = HttpStatusCode.BadRequest
        )
        val stock: StockDTO? = stockService.getStockById(
          UUID.fromString(id)
        )?.toDTO()
        if (stock == null) {
          call.respondText(
            text = "No stock with id: $id",
            status = HttpStatusCode.NotFound
          )
        } else {
          call.respond(stock)
        }
      }

//            post {
//                val stockRequest = call.receive<StockToCreate>()
//                stockService.addStock(stockRequest)
//                call.respondText(
//                    text = "Stock stored correctly",
//                    status = HttpStatusCode.Accepted
//                )
//            }

      delete("/{id}") {
        val stockId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        stockService.deleteStock(stockId)
        call.respond(HttpStatusCode.OK)
      }
    }

  }
}