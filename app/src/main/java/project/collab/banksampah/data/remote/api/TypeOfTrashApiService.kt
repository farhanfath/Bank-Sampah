package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.response.type_trash.TypeOfTrashDetailResponseDto
import project.collab.banksampah.data.remote.model.response.type_trash.TypeOfTrashListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface TypeOfTrashApiService {
    suspend fun getTypeOfTrashList(
        page: Int,
        limit: Int = 10,
        trashType: String?
    ) : ResponseResult<TypeOfTrashListResponseDto>

    suspend fun getTypeOfTrashById(
        id: String
    ) : ResponseResult<TypeOfTrashDetailResponseDto>
}

class TypeOfTrashApiServiceImpl(
    private val httpClient: HttpClient
) : TypeOfTrashApiService {
    override suspend fun getTypeOfTrashList(
        page: Int,
        limit: Int,
        trashType: String?
    ): ResponseResult<TypeOfTrashListResponseDto> {
        return safeApiCall {
            val response : TypeOfTrashListResponseDto = httpClient.get(ApiEndpoint.TypeTrash.ALL) {
                parameter("page", page)
                parameter("limit", limit)
                trashType?.let { parameter("TrashType", it) }
            }.body()
            response
        }
    }

    override suspend fun getTypeOfTrashById(id: String): ResponseResult<TypeOfTrashDetailResponseDto> {
        return safeApiCall {
            val response : TypeOfTrashDetailResponseDto = httpClient.get(
                ApiEndpoint.TypeTrash.BY_ID.replace("{id}", id)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }

}