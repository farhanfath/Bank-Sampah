package project.collab.banksampah.domain.model.response.schedule

import project.collab.banksampah.domain.model.PaginationInfo

data class ScheduleListResult(
    val schedules: List<Schedule>,
    val pagination: PaginationInfo
)

data class Schedule(
    val id: String,
    val addedAt: String,
    val bsuBranchCode: String,
    val bsuBranchName: String,
    val closeAt: String,
    val openAt: String,
    val editor: String
)