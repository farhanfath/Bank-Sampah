package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.domain.model.response.point_exchange.PointExchangeRequestResponse
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistory
import project.collab.banksampah.domain.utils.ResponseResult

interface ExchangeRepository {
    suspend fun getAllUserTrashExchangeHistory(
        page: Int,
        limit: Int = 10
    ): ResponseResult<List<TrashExchangeHistory>>

    suspend fun getUserTrashExchangeHistoryById(
        trashExchangeId: String
    ) : ResponseResult<TrashExchangeHistory>

    suspend fun postPointExchange(
        pointRequested: RedeemPointRequest
    ) : ResponseResult<PointExchangeRequestResponse>
}