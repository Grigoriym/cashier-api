package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategory(
    val id: Long,
    val name: String,
    val merchantId: String,
    val stockId: String
)