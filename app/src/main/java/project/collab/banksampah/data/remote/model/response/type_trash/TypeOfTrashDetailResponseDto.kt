package project.collab.banksampah.data.remote.model.response.type_trash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeOfTrashDetailResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: TypeOfTrashItemDto
)