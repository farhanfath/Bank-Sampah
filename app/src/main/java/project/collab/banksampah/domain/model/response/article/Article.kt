package project.collab.banksampah.domain.model.response.article

import project.collab.banksampah.domain.model.PaginationInfo

data class ArticleListResult(
    val articles: List<Article>,
    val pagination: PaginationInfo
)

data class Article(
    val id: String,
    val title: String,
    val coverImageFileName: String,
    val coverImageUrl: String,
    val imageFileNames: List<String>,
    val imageUrls: List<String>,
    val description: String,
    val editor: String,
    val timestamp: String
)

