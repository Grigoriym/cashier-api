package com.grappim.routes

import com.grappim.authentication.jwt.getMerchantId
import com.grappim.common_data.model.feature_toggle.CreateFeatureToggleRequestDTO
import com.grappim.domain.service.FeatureToggleService
import com.grappim.mappers.toDTO
import com.grappim.utils.toUUID
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Route.featureToggleRouting() {

  val featureToggleService by closestDI().instance<FeatureToggleService>()

  route("/feature") {
    authenticate {
      get {
        val merchantId = getMerchantId()
        val stockId = call.parameters["stockId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val featureToggle = featureToggleService.getFeatureToggle(
          merchantId = merchantId.toUUID(),
          stockId = stockId.toUUID()
        )
        call.respond(featureToggle.toDTO())
      }
      post("/create") {
        val request = call.receive<CreateFeatureToggleRequestDTO>()
        val created = featureToggleService.createFeatureToggle(
          merchantId = request.merchantId,
          stockId = request.stockId
        )
        call.respond(created.toDTO())
      }
      post("update") {

      }
    }
  }

}