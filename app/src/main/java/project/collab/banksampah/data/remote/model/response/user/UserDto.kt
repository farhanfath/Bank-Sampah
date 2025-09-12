package project.collab.banksampah.data.remote.model.response.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val message: String,
    val data: UserData,
)

@Serializable
data class UserData(
    @SerialName("_id")
    val id: String,
    val name: String,
    val number: String,
    val nik: String,
    val address: String,
    val bsuBranchName: String,
    val bsuBranchCode: String,
    val password: String,
    val totalPointUser: Int,
    val currentPointUser: Int,
    val pointUserAlreadyRedeem: Int,
    val role : String,
    val timeStamp: String,
    val userNotification: List<UserNotificationItem>,
    val userTrashExchangeHistory: List<UserTrashExchangeHistoryItem>,
//    val userPointTransactionHistories: List<Any>
    @SerialName("__v")
    val v: Int,
)

@Serializable
data class UserNotificationItem(
    @SerialName("_id")
    val id: String,
    val timeStamp: String,
    val notificationTitle: String,
    val description: String,
    val statusNotification: String
)

@Serializable
data class UserTrashExchangeHistoryItem(
    val bsuBranchName: String,
    val exchangeDate: String,
    val totalPoint: Int,
    val listOfTrash: List<ListOfTrashItem>,
    val kaderName: String,
    @SerialName("_id")
    val id: String,
    val exchangeCode: String
)

@Serializable
data class ListOfTrashItem(
    val unitPrice: Int,
    val amount: Int,
    val unit: String,
    val totalPrice: Int,
    @SerialName("TrashType")
    val trashType: String,
    @SerialName("_id")
    val id: String
)
