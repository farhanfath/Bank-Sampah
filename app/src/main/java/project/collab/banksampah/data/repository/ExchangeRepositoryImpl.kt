package project.collab.banksampah.data.repository

import project.collab.banksampah.data.local.TokenDataSource
import project.collab.banksampah.data.remote.api.ExchangeApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.data.remote.model.mapper.toDto
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.domain.model.response.point_exchange.PointExchangeRequestResponse
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistory
import project.collab.banksampah.domain.repository.ExchangeRepository
import project.collab.banksampah.domain.utils.ResponseResult

class ExchangeRepositoryImpl (
    private val exchangeApiService: ExchangeApiService,
    private val tokenDataSource: TokenDataSource
) : ExchangeRepository {
    override suspend fun getAllUserTrashExchangeHistory(
        page: Int,
        limit: Int
    ): ResponseResult<List<TrashExchangeHistory>> {
        val userId = tokenDataSource.getUserId() ?: ""
        val result = exchangeApiService.getAllUserTrashExchangeHistory(page, limit, userId)

        return when(result) {
            is ResponseResult.Success -> {
                val trashExchangeHistories = result.data.data.histories.map { it.toDomain() }
                ResponseResult.Success(trashExchangeHistories)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getUserTrashExchangeHistoryById(
        trashExchangeId: String
    ): ResponseResult<TrashExchangeHistory> {
        val userId = tokenDataSource.getUserId() ?: ""
        val result = exchangeApiService.getUserTrashExchangeHistoryById(userId, trashExchangeId)
        return when(result) {
            is ResponseResult.Success -> {
                val trashExchangeHistory = result.data.data.toDomain()
                ResponseResult.Success(trashExchangeHistory)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun postPointExchange(
        pointRequested: RedeemPointRequest
    ): ResponseResult<PointExchangeRequestResponse> {
        val request = pointRequested.toDto()
        val result = exchangeApiService.postPointExchange(request)

        return when (result) {
            is ResponseResult.Success -> {
                val pointExchangeRequestResponse = result.data.toDomain()
                ResponseResult.Success(pointExchangeRequestResponse)
            }
            is ResponseResult.Error -> result
        }
    }
}