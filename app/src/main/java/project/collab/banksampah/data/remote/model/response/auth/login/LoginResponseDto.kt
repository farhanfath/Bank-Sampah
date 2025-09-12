package project.collab.banksampah.data.remote.model.response.auth.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("message")
    val message: String?,
    @SerialName("meesage")
    val meesage: String? = null,
    @SerialName("status")
    val status: Boolean,
    @SerialName("token")
    val token: String?,
    @SerialName("_id")
    val id: String?
) {
    fun getActualMessage(): String? {
        return message ?: meesage
    }
}