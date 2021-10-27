package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val phone: String,
    val email: String,
    val password: String,
    val id: String
)

@Serializable
data class RegisterUser(
    val phone: String,
    val email: String,
    val password: String
)

@Serializable
data class RegisterUserResponse(
    val phone: String,
    val email: String
)

@Serializable
data class LoginUser(
    val phone: String,
    val password: String
)

@Serializable
data class LoginUserResponse(
    val token: String,
    val merchantId: String,
    val merchantName: String
)

@Serializable
data class UpdateUser(val user: User) {
    @Serializable
    data class User(
        val phone: String,
        val email: String,
        val password: String
    )
}

@Serializable
data class DeleteUser(val user: User) {
    @Serializable
    data class User(
        val id: String
    )
}