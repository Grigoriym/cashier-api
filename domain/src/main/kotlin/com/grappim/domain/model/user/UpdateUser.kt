package com.grappim.domain.model.user

data class UpdateUser(val user: User) {
  data class User(
    val phone: String,
    val email: String,
    val password: String
  )
}