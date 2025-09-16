package project.collab.banksampah.data.remote.model.response.user


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditUserResponseDto(
    @SerialName("message")
    val message: String?,
    @SerialName("result")
    val result: Result?,
    @SerialName("status")
    val status: Boolean?
)

@Serializable
data class Result(
    @SerialName("acknowledged")
    val acknowledged: Boolean?,
    @SerialName("matchedCount")
    val matchedCount: Int?,
    @SerialName("modifiedCount")
    val modifiedCount: Int?,
    @SerialName("upsertedCount")
    val upsertedCount: Int?,
    @SerialName("upsertedId")
    val upsertedId: String?
)