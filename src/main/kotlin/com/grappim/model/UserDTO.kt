package com.grappim.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val phone: String,
    val email: String,
    val password: String,
    val id: String
)

@Serializable
data class RegisterUserDTO(
    val phone: String,
    val email: String,
    val password: String
)

@Serializable
data class RegisterUserResponseDTO(
    val phone: String,
    val email: String
)

@Serializable
data class LoginUserDTO(
    val mobile: String,
    val password: String
)

@Serializable
data class LoginUserResponseDTO(
    val token: String,
    val merchantId: String,
    val merchantName: String
)

@Serializable
data class UpdateUserDTO(val user: UserDTO) {
    @Serializable
    data class UserDTO(
        val phone: String,
        val email: String,
        val password: String
    )
}

@Serializable
data class DeleteUserDTO(val user: UserDTO) {
    @Serializable
    data class UserDTO(
        val id: String
    )
}