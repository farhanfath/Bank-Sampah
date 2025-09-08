package project.collab.banksampah.data.remote.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import project.collab.banksampah.data.remote.model.response.article.ArticleDetailResponseDto
import project.collab.banksampah.data.remote.model.response.article.ArticleListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface ArticleApiService {
    suspend fun getArticlesList(
        page: Int,
        limit: Int = 10,
        articleTitle: String?
    ): ResponseResult<ArticleListResponseDto>

    suspend fun getArticleById(
        id: String
    ) : ResponseResult<ArticleDetailResponseDto>
}

class ArticleApiServiceImpl(
    private val httpClient: HttpClient
) : ArticleApiService {
    override suspend fun getArticlesList(
        page: Int,
        limit: Int,
        articleTitle: String?
    ): ResponseResult<ArticleListResponseDto> {
        return safeApiCall {
            val response : ArticleListResponseDto = httpClient.get(ApiEndpoint.Article.ALL) {
                parameter("page", page)
                parameter("limit", limit)
                articleTitle?.let { parameter("acticleTitle", it) }
            }.body()
            response
        }
    }

    override suspend fun getArticleById(id: String): ResponseResult<ArticleDetailResponseDto> {
        return safeApiCall {
            val response : ArticleDetailResponseDto = httpClient.get(
                ApiEndpoint.Article.BY_ID.replace("{id}", id)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }
}