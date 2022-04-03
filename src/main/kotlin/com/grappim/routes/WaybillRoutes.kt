package com.grappim.routes

import com.grappim.common_data.model.base.BaseFilterDTO
import com.grappim.common_data.model.waybill.CreateWaybillRequestDTO
import com.grappim.common_data.model.waybill.CreateWaybillResponseDTO
import com.grappim.common_data.model.waybill.FilterWaybillsResponseDTO
import com.grappim.common_data.model.waybill.UpdateWaybillDTO
import com.grappim.common_data.model.waybill.product.GetWaybillByIdResponseDTO
import com.grappim.domain.service.WaybillService
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

fun Route.waybillRouting() {

  val waybillService by closestDI().instance<WaybillService>()

  route("/waybill") {
    authenticate {
      post("/create") {
        val request = call.receive<CreateWaybillRequestDTO>()
        val createdWaybill = waybillService.createWaybill(
          request.waybill.toDomain()
        ).toDTO()
        call.respond(
          CreateWaybillResponseDTO(
            waybill = createdWaybill
          )
        )
      }
      post("/filter") {
        val request = call.receive<BaseFilterDTO>()
        val waybills = waybillService.filterWaybills(request.toDomain())
        if (waybills.isNotEmpty()) {
          val waybillsToSend = FilterWaybillsResponseDTO(
            waybills = waybills.toDTO()
          )
          call.respond(waybillsToSend)
        } else {
          call.respondText(
            text = "No waybills found",
            status = HttpStatusCode.NotFound
          )
        }
      }
      put("/update") {
        val updateWaybillDTO = call.receive<UpdateWaybillDTO>()
        val waybill = updateWaybillDTO.waybill.toDomain()
        val updatedWaybill = waybillService.updateWaybill(waybill).toDTO()
        call.respond(GetWaybillByIdResponseDTO(updatedWaybill))
      }
      delete("/{id}") {
        val waybillId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

        waybillService.deleteWaybill(waybillId.toLong())

        call.respondText(
          text = "Waybill deleted",
          status = HttpStatusCode.OK
        )
      }
      get("/conduct/{id}") {
        val waybillId = call.parameters["id"]?.toLongOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
        waybillService.conductWaybill(waybillId)
        call.respondText(
          text = "Waybill conducted",
          status = HttpStatusCode.OK
        )
      }
      get("/rollback/{id}") {
        val waybillId = call.parameters["id"]?.toLongOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
        waybillService.rollbackWaybill(waybillId)
        call.respondText(
          text = "Waybill conducted",
          status = HttpStatusCode.OK
        )
      }
    }
  }

}