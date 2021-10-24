package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val username: String,
    val password: String,
    val id: String
)

@Serializable
data class RegisterUser(val user: User) {
    @Serializable
    data class User(
        val email: String,
        val username: String,
        val password: String
    )
}

@Serializable
data class LoginUser(val user: User) {
    @Serializable
    data class User(
        val email: String,
        val password: String
    )
}

@Serializable
data class UpdateUser(val user: User) {
    @Serializable
    data class User(
        val email: String,
        val username: String,
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