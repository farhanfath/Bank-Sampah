package project.collab.banksampah.data.remote.model.response.auth.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserNotification(
    @SerialName("description")
    val description: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("notificationTitle")
    val notificationTitle: String?,
    @SerialName("statusNotification")
    val statusNotification: String?,
    @SerialName("timeStamp")
    val timeStamp: String?
)