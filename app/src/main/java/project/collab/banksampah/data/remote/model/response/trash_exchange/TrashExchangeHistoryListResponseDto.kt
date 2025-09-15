package project.collab.banksampah.data.remote.model.response.trash_exchange


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class TrashExchangeHistoryListResponseDto(
    @SerialName("data")
    val data: TrashExchangeHistoryDataDto
)

@Serializable
data class TrashExchangeHistoryDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("histories")
    val histories: List<TrashExchangeHistoryItemDto>
)

@Serializable
data class TrashExchangeHistoryItemDto(
    @SerialName("bsuBranchName")
    val bsuBranchName: String?,
    @SerialName("exchangeCode")
    val exchangeCode: String?,
    @SerialName("exchangeDate")
    val exchangeDate: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("kaderName")
    val kaderName: String?,
    @SerialName("listOfTrash")
    val listOfTrash: List<ListOfTrashItemDto>,
    @SerialName("totalPoint")
    val totalPoint: Int?
)

@Serializable
data class ListOfTrashItemDto(
    @SerialName("amount")
    val amount: Int?,
    @SerialName("_id")
    val id: String?,
    @SerialName("totalPrice")
    val totalPrice: Int?,
    @SerialName("TrashType")
    val trashType: String?,
    @SerialName("unit")
    val unit: String?,
    @SerialName("unitPrice")
    val unitPrice: Int?
)