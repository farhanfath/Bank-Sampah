package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.schedule.ScheduleItemDto
import project.collab.banksampah.data.remote.model.response.schedule.ScheduleListResponseDto
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.domain.model.response.schedule.ScheduleListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun ScheduleItemDto.toDomain() : Schedule {
    return Schedule(
        id = id.replaceIfNull(),
        addedAt = addedAt.replaceIfNull(),
        bsuBranchCode = bsuBranchCode.replaceIfNull(),
        bsuBranchName = bsuBranchName.replaceIfNull(),
        closeAt = closeAt.replaceIfNull(),
        openAt = openAt.replaceIfNull(),
        editor = editor.replaceIfNull(),
    )
}

fun ScheduleListResponseDto.toDomain() : ScheduleListResult {
    return ScheduleListResult(
        schedules = this.data.schedule.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()
    )
}