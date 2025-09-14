package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.GenericPagingSource
import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.domain.repository.BsuRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface BsuUseCase {
    fun getBsuList(bsuBranchName: String? = null) : Flow<PagingData<Bsu>>
    suspend fun getBsuById(id: String) : ResponseResult<Bsu>
}

class BsuUseCaseImpl(
    private val bsuRepository: BsuRepository
) : BsuUseCase {
    override fun getBsuList(bsuBranchName: String?): Flow<PagingData<Bsu>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GenericPagingSource { page, pageSize ->
                    bsuRepository.getBsuList(page, pageSize, bsuBranchName)
                }
            }
        ).flow
    }

    override suspend fun getBsuById(id: String): ResponseResult<Bsu> =
        bsuRepository.getBsuById(id)

}