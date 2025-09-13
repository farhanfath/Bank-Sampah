package project.collab.banksampah.data.repository

import project.collab.banksampah.data.remote.api.GalleryApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.domain.repository.GalleryRepository
import project.collab.banksampah.domain.utils.ResponseResult

class GalleryRepositoryImpl (
    private val apiService: GalleryApiService
) : GalleryRepository {
    override suspend fun getGalleriesList(
        page: Int,
        limit: Int,
        galleryTitle: String?
    ): ResponseResult<List<Gallery>> {
        val result = apiService.getGalleriesList(page, limit, galleryTitle)

        return when(result) {
            is ResponseResult.Success -> {
                val galleries = result.data.data.gallery.map { it.toDomain() }
                ResponseResult.Success(galleries)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getGalleryById(id: String): ResponseResult<Gallery> {
        val result = apiService.getGalleryById(id)

        return when(result) {
            is ResponseResult.Success -> {
                val articleDetail = result.data.data.toDomain()
                ResponseResult.Success(articleDetail)
            }
            is ResponseResult.Error -> result
        }
    }


}