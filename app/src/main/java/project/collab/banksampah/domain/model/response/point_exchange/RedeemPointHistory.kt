package project.collab.banksampah.domain.model.response.point_exchange

import project.collab.banksampah.domain.model.PaginationInfo

data class RedeemPointHistoryListResult(
    val histories : List<RedeemPointHistory>,
    val pagination: PaginationInfo
)

data class RedeemPointHistory(
    val id: String,
    val acceptDate: String,
    val description: String,
    val editor: String,
    val pointExchangeAccepted: String,
    val pointExchangeRequest: Int,
    val requestDate: String,
    val statusPointExchange: String,
    val transactionCode: String
)

