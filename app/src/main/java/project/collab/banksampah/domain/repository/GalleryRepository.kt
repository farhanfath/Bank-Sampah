package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.domain.utils.ResponseResult

interface GalleryRepository {
    suspend fun getGalleriesList(
        page: Int,
        limit: Int,
        galleryTitle: String? = null
    ): ResponseResult<List<Gallery>>

    suspend fun getGalleryById(id: String): ResponseResult<Gallery>
}