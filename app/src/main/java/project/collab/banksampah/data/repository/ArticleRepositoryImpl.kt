package project.collab.banksampah.data.repository

import project.collab.banksampah.data.remote.api.ArticleApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.repository.ArticleRepository
import project.collab.banksampah.domain.utils.ResponseResult

class ArticleRepositoryImpl (
    private val apiService: ArticleApiService
) : ArticleRepository {
    override suspend fun getArticlesList(
        page: Int,
        limit: Int,
        articleTitle: String?
    ): ResponseResult<List<Article>> {
        val result = apiService.getArticlesList(page, limit, articleTitle)

        return when(result) {
            is ResponseResult.Success -> {
                val articles = result.data.data.article.map { it.toDomain() }
                ResponseResult.Success(articles)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getArticleById(id: String): ResponseResult<Article> {
        val result = apiService.getArticleById(id)

        return when(result) {
            is ResponseResult.Success -> {
                val articleDetail = result.data.data.toDomain()
                ResponseResult.Success(articleDetail)
            }
            is ResponseResult.Error -> result
        }
    }

}