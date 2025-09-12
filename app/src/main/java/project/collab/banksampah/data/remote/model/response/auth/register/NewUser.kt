package project.collab.banksampah.data.remote.model.response.auth.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewUser(
    @SerialName("address")
    val address: String?,
    @SerialName("bsuBranchCode")
    val bsuBranchCode: String?,
    @SerialName("bsuBranchName")
    val bsuBranchName: String?,
    @SerialName("currentPointUser")
    val currentPointUser: Int?,
    @SerialName("_id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("nik")
    val nik: String?,
    @SerialName("number")
    val number: String?,
    @SerialName("password")
    val password: String?,
    @SerialName("pointUserAlreadyRedeem")
    val pointUserAlreadyRedeem: Int?,
    @SerialName("role")
    val role: String?,
    @SerialName("timeStamp")
    val timeStamp: String?,
    @SerialName("totalPointUser")
    val totalPointUser: Int?,
    @SerialName("userNotification")
    val userNotification: List<UserNotification?>?,
    @SerialName("userPointTransactionHistories")
    val userPointTransactionHistories: List<String?>?,
    @SerialName("userTrashExchangeHistory")
    val userTrashExchangeHistory: List<String?>?,
    @SerialName("__v")
    val v: Int?
)