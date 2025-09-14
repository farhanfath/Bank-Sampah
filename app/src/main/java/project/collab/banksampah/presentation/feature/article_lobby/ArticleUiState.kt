package project.collab.banksampah.presentation.feature.article_lobby

import project.collab.banksampah.domain.model.response.article.Article

data class ArticleUiState(
    val isLoading: Boolean = false,
    val data: Article? = null,
    val error: String? = null,
    val searchQuery : String = ""
)