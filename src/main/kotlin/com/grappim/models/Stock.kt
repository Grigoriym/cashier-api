package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val id: String,
    val merchantId: String,
    val name: String
)

@Serializable
data class StocksResponse(
    val stocks: List<Stock>
)

@Serializable
data class StockToCreate(
    val merchantId: String,
    val name: String
)