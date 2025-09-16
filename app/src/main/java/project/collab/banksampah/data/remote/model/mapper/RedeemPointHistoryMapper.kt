package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.point_exchange.history.RedeemPointItemDto
import project.collab.banksampah.data.remote.model.response.point_exchange.history.RedeemPointResponseDto
import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistory
import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistoryListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun RedeemPointItemDto.toDomain() : RedeemPointHistory {
    return RedeemPointHistory(
        id = id.replaceIfNull(),
        acceptDate = acceptDate.replaceIfNull(),
        description = description.replaceIfNull(),
        editor = editor.replaceIfNull(),
        pointExchangeAccepted = pointExchangeAccepted.replaceIfNull(),
        pointExchangeRequest = pointExchangeRequest.replaceIfNull(),
        requestDate = requestDate.replaceIfNull(),
        statusPointExchange = statusPointExchange.replaceIfNull(),
        transactionCode = transactionCode.replaceIfNull()
    )
}

fun RedeemPointResponseDto.toDomain() : RedeemPointHistoryListResult {
    return RedeemPointHistoryListResult(
        histories = this.data.histories.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()
    )
}