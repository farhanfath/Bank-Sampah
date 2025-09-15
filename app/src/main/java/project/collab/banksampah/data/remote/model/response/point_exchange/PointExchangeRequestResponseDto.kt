package project.collab.banksampah.data.remote.model.response.point_exchange


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointExchangeRequestResponseDto(
    @SerialName("data")
    val data: PointExchangeResponseDataDto?,
    @SerialName("message")
    val message: String?
)

@Serializable
data class PointExchangeResponseDataDto(
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
    @SerialName("timeStamp")
    val timeStamp: String?,
    @SerialName("transactionCode")
    val transactionCode: String?,
    @SerialName("transactionStatus")
    val transactionStatus: String?,
    @SerialName("userBranchName")
    val userBranchName: String?,
    @SerialName("userName")
    val userName: String?,
    @SerialName("__v")
    val v: Int?
)