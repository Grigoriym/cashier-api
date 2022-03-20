package com.grappim.data_service.model.user

import com.grappim.utils.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

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
  @Serializable(UUIDSerializer::class)
  val merchantId: UUID,
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