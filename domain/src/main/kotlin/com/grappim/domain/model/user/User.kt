package com.grappim.domain.model.user

import java.util.UUID

data class User(
  val phone: String,
  val email: String,
  val password: String,
  val id: UUID
)
