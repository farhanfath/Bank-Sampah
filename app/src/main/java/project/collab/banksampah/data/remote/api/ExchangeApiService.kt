package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.request.PointExchangeRequestDto
import project.collab.banksampah.data.remote.model.response.point_exchange.PointExchangeRequestResponseDto
import project.collab.banksampah.data.remote.model.response.point_exchange.history.RedeemPointResponseDto
import project.collab.banksampah.data.remote.model.response.trash_exchange.TrashExchangeHistoryDetailResponseDto
import project.collab.banksampah.data.remote.model.response.trash_exchange.TrashExchangeHistoryListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.replacePlaceholders
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface ExchangeApiService {
    suspend fun getAllUserTrashExchangeHistory(
        page: Int,
        limit: Int = 10,
        userId: String
    ) : ResponseResult<TrashExchangeHistoryListResponseDto>

    suspend fun getUserTrashExchangeHistoryById(
        userId: String,
        trashExchangeId: String
    ) : ResponseResult<TrashExchangeHistoryDetailResponseDto>

    suspend fun postPointExchange(
        request: PointExchangeRequestDto
    ) : ResponseResult<PointExchangeRequestResponseDto>

    suspend fun getAllUserPointRedeemHistory(
        page: Int,
        limit: Int = 10,
        userId: String
    ) : ResponseResult<RedeemPointResponseDto>
}

class ExchangeApiServiceImpl(
    private val httpClient: HttpClient
) : ExchangeApiService {
    override suspend fun getAllUserTrashExchangeHistory(
        page: Int,
        limit: Int,
        userId: String
    ): ResponseResult<TrashExchangeHistoryListResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.ALL_USER_TRASH_EXCHANGE_HISTORY.replacePlaceholders(
                "userId" to userId
            )

            val response : TrashExchangeHistoryListResponseDto = httpClient.get(url) {
                parameter("page", page)
                parameter("limit", limit)
            }.body()
            response
        }
    }

    override suspend fun getUserTrashExchangeHistoryById(
        userId: String,
        trashExchangeId: String
    ): ResponseResult<TrashExchangeHistoryDetailResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.USER_TRASH_EXCHANGE_HISTORY_BY_ID.replacePlaceholders(
                "userId" to userId,
                "trashExchangeId" to trashExchangeId
            )
            val response : TrashExchangeHistoryDetailResponseDto = httpClient.get(url).body()
            response
        }
    }

    override suspend fun postPointExchange(
        request: PointExchangeRequestDto
    ): ResponseResult<PointExchangeRequestResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.REQUEST_POINT_EXCHANGE

            val response : PointExchangeRequestResponseDto = httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            response
        }
    }

    override suspend fun getAllUserPointRedeemHistory(
        page: Int,
        limit: Int,
        userId: String
    ): ResponseResult<RedeemPointResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.ALL_USER_POINT_REDEEM_HISTORY.replacePlaceholders(
                "userId" to userId
            )

            val response : RedeemPointResponseDto = httpClient.get(url).body()
            response
        }
    }

}