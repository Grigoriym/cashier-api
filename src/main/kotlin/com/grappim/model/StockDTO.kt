package com.grappim.model

import kotlinx.serialization.Serializable

@Serializable
data class StockDTO(
    val id: String,
    val merchantId: String,
    val name: String
)

@Serializable
data class StocksResponseDTO(
    val stocks: List<StockDTO>
)

@Serializable
data class StockToCreateDTO(
    val merchantId: String,
    val name: String
)