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

@Serializable
data class ProductDoesNotExist(
  @SerialName("message")
  override val message: String = "Product was not found",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.PRODUCT_DOES_NOT_EXIST
) : IOException()

@Serializable
data class ProductCategoryDoesNotExist(
  @SerialName("message")
  override val message: String = "Product category was not found",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.PRODUCT_CATEGORY_DOES_NOT_EXIST
) : IOException()

@Serializable
data class ProductNameIsEmptyException(
  @SerialName("message")
  override val message: String = "Product category name must not be empty",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.Category.PRODUCT_CATEGORY_NAME_IS_EMPTY
) : IOException()


@Serializable
data class WaybillDoesNotExist(
  @SerialName("message")
  override val message: String = "Waybill was not found",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.WAYBILL_DOES_NOT_EXIST
) : IOException()

@Serializable
data class DuplicateProductNameException(
  val name: String,
  @SerialName("message")
  override val message: String = "Product with that name: $name already exists",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.Product.DUPLICATE_PRODUCT_NAME
) : IOException()

@Serializable
data class DuplicateProductBarcodeException(
  val barcode: String,
  @SerialName("message")
  override val message: String = "Product with that barcode: $barcode already exists",
  @SerialName("statusCode")
  val statusCode: String = ErrorStatusCodes.Product.DUPLICATE_PRODUCT_BARCODE
) : IOException()

class AuthenticationException : RuntimeException()

open class ApiError(
  val status: Int,
  val type: String,
  val title: String,
  val detail: String,
  val instance: String,
  val path: String,
  val timestamp: OffsetDateTime = OffsetDateTime.now()
) : RuntimeException()