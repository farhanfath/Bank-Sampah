package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.response.user.UserDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface UserApiService {
    suspend fun getUserById(userId: String): ResponseResult<UserDto>
}

class UserApiServiceImpl(
    private val httpClient: HttpClient
): UserApiService {
    override suspend fun getUserById(userId: String): ResponseResult<UserDto> {
        return safeApiCall {
            val response : UserDto = httpClient.get(
                ApiEndpoint.User.BY_ID.replace("{id}", userId)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }

}