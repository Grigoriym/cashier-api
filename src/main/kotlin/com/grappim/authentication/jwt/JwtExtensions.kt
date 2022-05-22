package com.grappim.authentication.jwt

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.getMerchantId(): String =
  call.principal<JWTPrincipal>()
    ?.payload
    ?.getClaim(JwtControllerImpl.CLAIM_ID)
    ?.asString() ?: throw IllegalArgumentException("can not get id from token")