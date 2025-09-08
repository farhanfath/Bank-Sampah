package project.collab.banksampah.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.repository.ArticleRepository
import project.collab.banksampah.domain.utils.ResponseResult

class ArticlesPagingSource (
    private val articleRepository: ArticleRepository,
    private val articleTitle: String? = null
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val result = articleRepository.getArticlesList(
                page = page,
                limit = pageSize,
                articleTitle = articleTitle
            )

            when (result) {
                is ResponseResult.Success -> {
                    val articles = result.data

                    LoadResult.Page(
                        data = articles,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (articles.isEmpty() || articles.size < pageSize) null else page + 1
                    )
                }
                is ResponseResult.Error -> {
                    LoadResult.Error(result.exception)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}