package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val id: String,
    val merchantId: String,
    val name: String
)

@Serializable
data class StockToCreate(
    val merchantId: String,
    val name: String
)