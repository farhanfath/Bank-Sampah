package project.collab.banksampah.data.remote.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import project.collab.banksampah.data.remote.model.request.LoginRequestDto
import project.collab.banksampah.data.remote.model.request.RegisterRequestDto
import project.collab.banksampah.data.remote.model.response.auth.login.LoginResponseDto
import project.collab.banksampah.data.remote.model.response.auth.register.RegisterResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface AuthApiService {
    suspend fun registerUser(request: RegisterRequestDto): ResponseResult<RegisterResponseDto>
    suspend fun loginUser(request: LoginRequestDto): ResponseResult<LoginResponseDto>
}

class AuthApiServiceImpl(
    private val httpClient: HttpClient
) : AuthApiService {
    override suspend fun registerUser(request: RegisterRequestDto): ResponseResult<RegisterResponseDto> = safeApiCall {
            val response : RegisterResponseDto = httpClient.post(ApiEndpoint.Auth.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            if (response.status) response else throw Exception(response.message)
    }

    override suspend fun loginUser(request: LoginRequestDto): ResponseResult<LoginResponseDto> = safeApiCall {
        try {
            val response: LoginResponseDto = httpClient.post(ApiEndpoint.Auth.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()

            Log.d("AuthApiService", "Login response: $response")

            if (response.status) {
                response
            } else {
                val errorMessage = response.getActualMessage() ?: "Login failed"
                throw Exception(errorMessage)
            }
        } catch (e: Exception) {
            Log.e("AuthApiService", "Login error: ${e.message}")
            throw e
        }
    }

}