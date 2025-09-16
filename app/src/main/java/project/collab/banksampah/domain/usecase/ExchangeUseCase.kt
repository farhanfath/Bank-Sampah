package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.GenericPagingSource
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.domain.model.response.point_exchange.PointExchangeRequestResponse
import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistory
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistory
import project.collab.banksampah.domain.repository.ExchangeRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface ExchangeUseCase {
    fun getAllUserTrashExchangeHistory() : Flow<PagingData<TrashExchangeHistory>>
    suspend fun getTrashExchangeHistoryById(trashExchangeId: String) : ResponseResult<TrashExchangeHistory>
    suspend fun postRequestPointExchange(pointRequest: RedeemPointRequest) : ResponseResult<PointExchangeRequestResponse>
    fun getAllUserPointRedeemHistory() : Flow<PagingData<RedeemPointHistory>>
}

class ExchangeUseCaseImpl(
    private val repository: ExchangeRepository
) : ExchangeUseCase {
    override fun getAllUserTrashExchangeHistory(): Flow<PagingData<TrashExchangeHistory>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GenericPagingSource { page, pageSize ->
                    repository.getAllUserTrashExchangeHistory(page, pageSize)
                }
            }
        ).flow
    }

    override suspend fun getTrashExchangeHistoryById(trashExchangeId: String): ResponseResult<TrashExchangeHistory> =
        repository.getUserTrashExchangeHistoryById(trashExchangeId)

    override suspend fun postRequestPointExchange(pointRequest: RedeemPointRequest): ResponseResult<PointExchangeRequestResponse> =
        repository.postPointExchange(pointRequest)

    override fun getAllUserPointRedeemHistory(): Flow<PagingData<RedeemPointHistory>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GenericPagingSource { page, pageSize ->
                    repository.getAllUserPointRedeemHistory(page, pageSize)
                }
            }
        ).flow
    }

}