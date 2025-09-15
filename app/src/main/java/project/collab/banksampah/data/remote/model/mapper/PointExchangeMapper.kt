package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.request.PointExchangeRequestDto
import project.collab.banksampah.data.remote.model.response.point_exchange.PointExchangeRequestResponseDto
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.domain.model.response.point_exchange.PointExchangeRequestResponse
import project.collab.banksampah.presentation.utils.replaceIfNull

fun RedeemPointRequest.toDto() : PointExchangeRequestDto {
    return PointExchangeRequestDto(
        pointExchangeRequest = pointToRedeem
    )
}

fun PointExchangeRequestResponseDto.toDomain() : PointExchangeRequestResponse {
    return PointExchangeRequestResponse(
        message = message.replaceIfNull(),
        id = data?.id.replaceIfNull(),
        acceptDate = data?.acceptDate.replaceIfNull(),
        description = data?.description.replaceIfNull(),
        editor = data?.editor.replaceIfNull(),
        pointExchangeAccepted = data?.pointExchangeAccepted.replaceIfNull(),
        pointExchangeRequest = data?.pointExchangeRequest.replaceIfNull(),
        requestDate = data?.requestDate.replaceIfNull(),
        timeStamp = data?.timeStamp.replaceIfNull(),
        transactionCode = data?.transactionCode.replaceIfNull(),
        transactionStatus = data?.transactionStatus.replaceIfNull(),
        userBranchName = data?.userBranchName.replaceIfNull(),
        userName = data?.userName.replaceIfNull()
    )
}