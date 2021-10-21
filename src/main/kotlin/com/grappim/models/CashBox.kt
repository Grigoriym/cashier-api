package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class CashBox(
    val name: String,
    val cashBoxId: String,
    val merchantId: String,
    val stockId: String
)

@Serializable
data class CashBoxToAdd(
    val name: String,
    val merchantId: String,
    val stockId: String
)
