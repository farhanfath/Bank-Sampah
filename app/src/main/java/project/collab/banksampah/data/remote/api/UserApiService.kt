package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.request.EditPassRequestDto
import project.collab.banksampah.data.remote.model.request.EditUserRequestDto
import project.collab.banksampah.data.remote.model.response.user.EditPassResponseDto
import project.collab.banksampah.data.remote.model.response.user.EditUserResponseDto
import project.collab.banksampah.data.remote.model.response.user.UserDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.replacePlaceholders
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface UserApiService {
    suspend fun getUserById(userId: String): ResponseResult<UserDto>

    suspend fun patchEditUserData(
        userId: String,
        request: EditUserRequestDto
    ): ResponseResult<EditUserResponseDto>

    suspend fun postEditPassword(
        userId: String,
        passRequest: EditPassRequestDto
    ) : ResponseResult<EditPassResponseDto>
}

class UserApiServiceImpl(
    private val httpClient: HttpClient
): UserApiService {
    override suspend fun getUserById(userId: String): ResponseResult<UserDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.BY_ID.replacePlaceholders("userId" to userId)

            val response : UserDto = httpClient.get(url).body()
            response
        }
    }

    override suspend fun patchEditUserData(
        userId: String,
        request: EditUserRequestDto
    ): ResponseResult<EditUserResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.EDIT_USER.replacePlaceholders("userId" to userId)

            val response : EditUserResponseDto = httpClient.patch(url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            response
        }
    }

    override suspend fun postEditPassword(
        userId: String,
        passRequest: EditPassRequestDto
    ): ResponseResult<EditPassResponseDto> {
        return safeApiCall {
            val url = ApiEndpoint.User.CHANGE_PASSWORD.replacePlaceholders("userId" to userId)

            val response : EditPassResponseDto = httpClient.post(url) {
                contentType(ContentType.Application.Json)
                setBody(passRequest)
            }.body()
            response
        }
    }

}