package com.grappim.utils

object ErrorStatusCodes {

  private const val CASHIER_API = "CASHIER.API"

  object Waybill {

  }

  object Category {
    private const val EMPTY_NAME = "7100"

    const val PRODUCT_CATEGORY_NAME_IS_EMPTY = "$CASHIER_API.$EMPTY_NAME"
  }

  object Product {
    private const val DUPLICATE_NAME = "7000"
    private const val DUPLICATE_BARCODE = "7001"

    const val DUPLICATE_PRODUCT_NAME = "$CASHIER_API.${DUPLICATE_NAME}"
    const val DUPLICATE_PRODUCT_BARCODE = "$CASHIER_API.${DUPLICATE_BARCODE}"
  }

  object User {
    private const val USER_EXISTS = "8000"

    const val USER_EXISTS_ERROR = "$CASHIER_API.$USER_EXISTS"
  }

  const val USER_DOES_NOT_EXIST = "$CASHIER_API.6900"

  const val MERCHANT_ID_IS_EMPTY = "$CASHIER_API.6901"
  const val STOCK_ID_IS_EMPTY = "$CASHIER_API.6902"

  const val PRODUCT_DOES_NOT_EXIST = "$CASHIER_API.6903"
  const val PRODUCT_CATEGORY_DOES_NOT_EXIST = "$CASHIER_API.6904"

  const val WAYBILL_DOES_NOT_EXIST = "$CASHIER_API.6905"

}