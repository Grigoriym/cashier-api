package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val stockId: String,
    val merchantId: String,
    val stockName: String
)

@Serializable
data class StockToCreate(
    val merchantId: String,
    val stockName: String
)