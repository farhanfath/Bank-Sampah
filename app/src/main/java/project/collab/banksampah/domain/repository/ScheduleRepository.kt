package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.domain.utils.ResponseResult

interface ScheduleRepository {
    suspend fun getSchedulesList(
        page: Int,
        limit: Int,
        bsuBranchName: String? = null
    ): ResponseResult<List<Schedule>>

    suspend fun getScheduleById(id: String): ResponseResult<Schedule>
}