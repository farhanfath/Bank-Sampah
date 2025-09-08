package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.utils.ResponseResult

interface ArticleRepository {
    suspend fun getArticlesList(
        page: Int,
        limit: Int,
        articleTitle: String? = null
    ): ResponseResult<List<Article>>

    suspend fun getArticleById(id: String): ResponseResult<Article>
}