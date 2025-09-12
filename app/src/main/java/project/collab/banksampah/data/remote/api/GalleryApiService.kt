package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.response.gallery.GalleryDetailResponseDto
import project.collab.banksampah.data.remote.model.response.gallery.GalleryListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface GalleryApiService {
    suspend fun getGalleriesList(
        page: Int,
        limit: Int = 10,
        galleryTitle: String?
    ): ResponseResult<GalleryListResponseDto>

    suspend fun getGalleryById(
        id: String
    ) : ResponseResult<GalleryDetailResponseDto>
}

class GalleryApiServiceImpl(
    private val httpClient: HttpClient
) : GalleryApiService {
    override suspend fun getGalleriesList(
        page: Int,
        limit: Int,
        galleryTitle: String?
    ): ResponseResult<GalleryListResponseDto> {
        return safeApiCall {
            val response : GalleryListResponseDto = httpClient.get(ApiEndpoint.Gallery.ALL) {
                parameter("page", page)
                parameter("limit", limit)
                galleryTitle?.let { parameter("imgTitle", it) }
            }.body()
            response
        }
    }

    override suspend fun getGalleryById(id: String): ResponseResult<GalleryDetailResponseDto> {
        return safeApiCall {
            val response : GalleryDetailResponseDto = httpClient.get(
                ApiEndpoint.Gallery.BY_ID.replace("{id}", id)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }

}