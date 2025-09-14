package project.collab.banksampah.data.repository

import project.collab.banksampah.data.remote.api.ScheduleApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.domain.repository.ScheduleRepository
import project.collab.banksampah.domain.utils.ResponseResult

class ScheduleRepositoryImpl (
    private val apiService: ScheduleApiService
) : ScheduleRepository {
    override suspend fun getSchedulesList(
        page: Int,
        limit: Int,
        bsuBranchName: String?
    ): ResponseResult<List<Schedule>> {
        val result = apiService.getSchedulesList(page, limit, bsuBranchName)

        return when(result) {
            is ResponseResult.Success -> {
                val schedules = result.data.data.schedule.map { it.toDomain() }
                ResponseResult.Success(schedules)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getScheduleById(id: String): ResponseResult<Schedule> {
        val result = apiService.getScheduleById(id)

        return when(result) {
            is ResponseResult.Success -> {
                val scheduleDetail = result.data.data.toDomain()
                ResponseResult.Success(scheduleDetail)
            }
            is ResponseResult.Error -> result
        }
    }

}