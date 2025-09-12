package project.collab.banksampah.data.remote.model.response.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import project.collab.banksampah.data.remote.model.response.PaginationDto

@Serializable
data class ArticleListResponseDto(
    @SerialName("data")
    val data : ArticleDataDto
)

@Serializable
data class ArticleDataDto(
    @SerialName("pagination")
    val pagination: PaginationDto,
    @SerialName("article")
    val article: List<ArticleItemDto>
)

@Serializable
data class ArticleItemDto(
    @SerialName("_id")
    val id : String,

    @SerialName("acticleTitle")
    val articleTitle: String,
    val photoCoverFileName: String,
    val photoCoverFileURL: String,
    val images: List<String>,

    @SerialName("ImagesURL")
    val imagesURL: List<String>,

    val description: String,
    val editor: String,
    val timeStamp: String,

    @SerialName("__v")
    val version: Int
)