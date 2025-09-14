package project.collab.banksampah.data.remote.model.response.bsu

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class BsuListResponseDto(
    @SerialName("data")
    val data: BsuDataDto
)

@Serializable
data class BsuDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("bsu")
    val bsu: List<BsuItemDto>
)