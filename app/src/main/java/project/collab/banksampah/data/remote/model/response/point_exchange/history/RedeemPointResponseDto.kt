package project.collab.banksampah.data.remote.model.response.point_exchange.history


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class RedeemPointResponseDto(
    @SerialName("data")
    val data: RedeemPointDataDto
)

@Serializable
data class RedeemPointDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("histories")
    val histories: List<RedeemPointItemDto>
)

@Serializable
data class RedeemPointItemDto(
    @SerialName("acceptDate")
    val acceptDate: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("editor")
    val editor: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("pointExchangeAccepted")
    val pointExchangeAccepted: String?,
    @SerialName("pointExchangeRequest")
    val pointExchangeRequest: Int?,
    @SerialName("requestDate")
    val requestDate: String?,
    @SerialName("statusPointExchange")
    val statusPointExchange: String?,
    @SerialName("transactionCode")
    val transactionCode: String?
)