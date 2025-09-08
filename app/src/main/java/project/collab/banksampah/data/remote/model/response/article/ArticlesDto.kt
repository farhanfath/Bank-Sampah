package project.collab.banksampah.data.remote.model.response.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleListResponseDto(
    val data : ArticleDataDto
)

@Serializable
data class ArticleDataDto(
    val pagination: PaginationDto,
    val article: List<ArticleItemDto>
)

@Serializable
data class PaginationDto(
    val total: Int,
    val limit: Int,
    val page: Int,
    val totalPages: Int
)

@Serializable
data class ArticleItemDto(
    @SerialName("_id")
    val id : String,

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