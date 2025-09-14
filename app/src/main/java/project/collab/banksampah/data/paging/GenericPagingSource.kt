package project.collab.banksampah.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.collab.banksampah.domain.utils.ResponseResult

class GenericPagingSource<T : Any>(
    private val fetchData: suspend (page: Int, pageSize: Int) -> ResponseResult<List<T>>
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            when (val result = fetchData(page, pageSize)) {
                is ResponseResult.Success -> {
                    val data = result.data
                    LoadResult.Page(
                        data = data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (data.isEmpty() || data.size < pageSize) null else page + 1
                    )
                }
                is ResponseResult.Error -> LoadResult.Error(result.exception)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
