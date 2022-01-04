package com.grappim.authentication.jwt

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.getMerchantId(): String =
  call.principal<JWTPrincipal>()
    ?.payload
    ?.getClaim(JwtControllerImpl.CLAIM_ID)
    ?.asString() ?: throw IllegalArgumentException("can not get id from token")