package project.collab.banksampah.data.remote.model.response.auth.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    @SerialName("message")
    val message: String?,
    @SerialName("newUser")
    val newUser: NewUser?,
    @SerialName("status")
    val status: Boolean
)