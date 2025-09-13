package project.collab.banksampah.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.repository.TypeOfTrashRepository
import project.collab.banksampah.domain.utils.ResponseResult

class TypeOfTrashPagingSource (
    private val typeOfTrashRepository: TypeOfTrashRepository,
    private val trashType: String? = null,
) : PagingSource<Int, TypeOfTrash>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TypeOfTrash> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val result = typeOfTrashRepository.getTypeOfTrashList(
                page = page,
                limit = pageSize,
                trashType = trashType
            )

            when (result) {
                is ResponseResult.Success -> {
                    val typeOfTrash = result.data

                    LoadResult.Page(
                        data = typeOfTrash,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (typeOfTrash.isEmpty() || typeOfTrash.size < pageSize) null else page + 1
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

    override fun getRefreshKey(state: PagingState<Int, TypeOfTrash>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}