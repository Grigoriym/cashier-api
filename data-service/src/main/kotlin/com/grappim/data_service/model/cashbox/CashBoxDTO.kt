package com.grappim.data_service.model.cashbox

import kotlinx.serialization.Serializable

@Serializable
data class CashBoxDTO(
    val name: String,
    val cashBoxId: String,
    val merchantId: String,
    val stockId: String
)

@Serializable
data class CashBoxResponseDTO(
    val cashBoxes: List<CashBoxDTO>
)

@Serializable
data class GetCashBoxesListDTO(
    val merchantId: String,
    val stockId: String
)