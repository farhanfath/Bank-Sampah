package project.collab.banksampah.data.remote.model.response.gallery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GalleryDetailResponseDto(
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: GalleryItemDto
)