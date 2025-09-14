package project.collab.banksampah.data.remote.model.response.gallery


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class GalleryListResponseDto(
    @SerialName("data")
    val data: GalleryDataDto
)

@Serializable
data class GalleryDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("gallery")
    val gallery: List<GalleryItemDto>
)

@Serializable
data class GalleryItemDto(
    @SerialName("description")
    val description: String?,
    @SerialName("editor")
    val editor: String?,
    @SerialName("fileName")
    val fileName: String?,
    @SerialName("fileURL")
    val fileURL: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("imgTitle")
    val imgTitle: String?,
    @SerialName("timeStamp")
    val timeStamp: String?,
    @SerialName("__v")
    val v: Int?
)