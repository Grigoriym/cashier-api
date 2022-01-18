package com.grappim.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.IOException
import java.time.OffsetDateTime

class UserExists : RuntimeException()

sealed class RegisterUserIncorrectFieldsException(
  open val exceptionMessage: String
) : RuntimeException() {
  data class BlankFieldsException(
    override val exceptionMessage: String
  ) : RegisterUserIncorrectFieldsException(exceptionMessage)
}

@Serializable
data class UserDoesNotExist(
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.USER_DOES_NOT_EXIST,
  @SerialName("message")
  override val message: String = "User does not exist",
  @SerialName("developerMessage")
  val developerMessage: String? = null
) : IOException()

@Serializable
class ProductCategoryDoesNotExist : IOException()

@Serializable
data class MerchantIdIsEmpty(
  @SerialName("message")
  override val message: String = "MerchantId must not be empty",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.MERCHANT_ID_IS_EMPTY
) : IOException()

@Serializable
data class StockIdIsEmpty(
  @SerialName("message")
  override val message: String = "StockId must not be empty",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.STOCK_ID_IS_EMPTY
) : IOException()

class AuthenticationException : RuntimeException()

object ProductNotFound : RuntimeException()

open class ApiError(
  val status: Int,
  val type: String,
  val title: String,
  val detail: String,
  val instance: String,
  val path: String,
  val timestamp: OffsetDateTime = OffsetDateTime.now()
) : RuntimeException()