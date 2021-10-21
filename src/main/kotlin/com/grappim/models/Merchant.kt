package com.grappim.models

import kotlinx.serialization.Serializable

@Serializable
data class Merchant(
    val merchantId: String,
    val merchantName: String,
    val mobile: String
)
