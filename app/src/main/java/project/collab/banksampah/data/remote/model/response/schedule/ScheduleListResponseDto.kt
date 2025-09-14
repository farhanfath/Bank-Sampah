package project.collab.banksampah.data.remote.model.response.schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class ScheduleListResponseDto(
    @SerialName("data")
    val data: ScheduleDataDto
)

@Serializable
data class ScheduleDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("Schedule")
    val schedule: List<ScheduleItemDto>
)

@Serializable
data class ScheduleItemDto(
    @SerialName("addedAt")
    val addedAt: String?,
    @SerialName("bsuBranchCode")
    val bsuBranchCode: String?,
    @SerialName("bsuBranchName")
    val bsuBranchName: String?,
    @SerialName("closeAt")
    val closeAt: String?,
    @SerialName("editor")
    val editor: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("openAt")
    val openAt: String?,
    @SerialName("__v")
    val v: Int?
)
