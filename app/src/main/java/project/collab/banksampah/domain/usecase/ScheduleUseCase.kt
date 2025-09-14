package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.GenericPagingSource
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.domain.repository.ScheduleRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface ScheduleUseCase {
    fun getScheduleList(bsuBranchName: String? = null) : Flow<PagingData<Schedule>>
    suspend fun getScheduleById(id: String): ResponseResult<Schedule>
}

class ScheduleUseCaseImpl(
    private val scheduleRepository: ScheduleRepository
) : ScheduleUseCase {
    override fun getScheduleList(bsuBranchName: String?): Flow<PagingData<Schedule>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GenericPagingSource { page, pageSize ->
                    scheduleRepository.getSchedulesList(page, pageSize, bsuBranchName)
                }
            }
        ).flow
    }

    override suspend fun getScheduleById(id: String): ResponseResult<Schedule> =
        scheduleRepository.getScheduleById(id)

}