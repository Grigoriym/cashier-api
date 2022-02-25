package com.grappim.domain.model.order

enum class PayType {
  CARD,
  CASH;

  companion object {
    fun getTypeFromString(text: String): PayType =
      when (text) {
        CARD.name -> CARD
        CASH.name -> CASH
        else -> error("incorrect payType: $text")
      }
  }
}