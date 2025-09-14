package project.collab.banksampah.data.remote.model.response.bsu


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BsuItemDto(
    @SerialName("address")
    val address: String?,
    @SerialName("bsuBranchCode")
    val bsuBranchCode: String?,
    @SerialName("bsuBranchName")
    val bsuBranchName: String?,
    @SerialName("editor")
    val editor: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("mapsAddress")
    val mapsAddress: String?,
    @SerialName("timeStamp")
    val timeStamp: String?,
    @SerialName("__v")
    val v: Int?
)