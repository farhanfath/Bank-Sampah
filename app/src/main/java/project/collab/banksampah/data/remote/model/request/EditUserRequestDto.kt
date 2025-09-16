package project.collab.banksampah.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditUserRequestDto(
    @SerialName("name")
    val name: String?,
    @SerialName("nik")
    val nik: String?,
    @SerialName("address")
    val address: String?
)