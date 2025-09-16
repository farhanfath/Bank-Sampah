package project.collab.banksampah.data.remote.model.response.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditPassResponseDto(
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: Boolean?,
    @SerialName("user")
    val user: UserData?
)