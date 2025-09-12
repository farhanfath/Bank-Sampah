package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.ArticlesPagingSource
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.repository.ArticleRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface ArticleUseCase {
    fun getArticlesList(articleTitle: String? = null) : Flow<PagingData<Article>>
    suspend fun getArticleById(id: String): ResponseResult<Article>
}

class ArticleUseCaseImpl(
    private val articleRepository: ArticleRepository
) : ArticleUseCase {
    override fun getArticlesList(articleTitle: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                ArticlesPagingSource(articleRepository, articleTitle)
            }
        ).flow
    }

    override suspend fun getArticleById(id: String): ResponseResult<Article> = articleRepository.getArticleById(id)

}