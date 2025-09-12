package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.GalleriesPagingSource
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.domain.repository.GalleryRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface GalleryUseCase {
    fun getGalleryList(galleryTitle: String? = null) : Flow<PagingData<Gallery>>
    suspend fun getGalleryById(id: String): ResponseResult<Gallery>
}

class GalleryUserCaseImpl(
    private val galleryRepository: GalleryRepository
) : GalleryUseCase {
    override fun getGalleryList(galleryTitle: String?): Flow<PagingData<Gallery>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GalleriesPagingSource(galleryRepository, galleryTitle)
            }
        ).flow
    }

    override suspend fun getGalleryById(id: String): ResponseResult<Gallery> = galleryRepository.getGalleryById(id)

}