package project.collab.banksampah.data.remote.model.response.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailResponseDto(
    val message: String,
    val data: ArticleItemDto
)