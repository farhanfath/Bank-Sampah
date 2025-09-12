package project.collab.banksampah.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.domain.repository.GalleryRepository
import project.collab.banksampah.domain.utils.ResponseResult

class GalleriesPagingSource (
    private val galleryRepository: GalleryRepository,
    private val galleryTitle: String? = null
) : PagingSource<Int, Gallery>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val result = galleryRepository.getGalleriesList(
                page = page,
                limit = pageSize,
                galleryTitle = galleryTitle
            )

            when (result) {
                is ResponseResult.Success -> {
                    val galleries = result.data

                    LoadResult.Page(
                        data = galleries,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (galleries.isEmpty() || galleries.size < pageSize) null else page + 1
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

    override fun getRefreshKey(state: PagingState<Int, Gallery>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}