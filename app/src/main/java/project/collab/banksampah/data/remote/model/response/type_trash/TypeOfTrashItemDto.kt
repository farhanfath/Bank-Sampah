package project.collab.banksampah.data.remote.model.response.type_trash


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class TypeOfTrashListResponseDto(
    @SerialName("data")
    val data: TypeOfTrashDataDto
)

@Serializable
data class TypeOfTrashDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("typeOfTrash")
    val typeOfTrash: List<TypeOfTrashItemDto>
)

@Serializable
data class TypeOfTrashItemDto(
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
    @SerialName("unitPrice")
    val price: Int?,
    @SerialName("timeStamp")
    val timeStamp: String?,
    @SerialName("TrashType")
    val trashType: String?,
    @SerialName("unit")
    val unit: String?,
    @SerialName("__v")
    val v: Int?
)

