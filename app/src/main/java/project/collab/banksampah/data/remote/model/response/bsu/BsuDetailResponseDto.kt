package project.collab.banksampah.data.remote.model.response.bsu

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BsuDetailResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: BsuItemDto
)