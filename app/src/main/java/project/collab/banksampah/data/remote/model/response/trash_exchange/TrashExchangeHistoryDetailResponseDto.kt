package project.collab.banksampah.data.remote.model.response.trash_exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrashExchangeHistoryDetailResponseDto(
    @SerialName("data")
    val data: TrashExchangeHistoryItemDto
)