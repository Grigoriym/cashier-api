package com.grappim.authentication.jwt

import com.grappim.domain.model.user.User

interface JwtController {

  fun getTokenForRespondAsString(user: User): String

}