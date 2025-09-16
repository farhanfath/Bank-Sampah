package project.collab.banksampah.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditPassRequestDto(
    @SerialName("password")
    val password: String,
    @SerialName("newPassword")
    val newPassword: String
)