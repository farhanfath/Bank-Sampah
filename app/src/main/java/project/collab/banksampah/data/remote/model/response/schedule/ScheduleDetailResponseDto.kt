package project.collab.banksampah.data.remote.model.response.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDetailResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: ScheduleItemDto
)