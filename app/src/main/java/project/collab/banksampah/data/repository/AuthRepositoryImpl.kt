package project.collab.banksampah.data.repository

import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.local.TokenDataSource
import project.collab.banksampah.data.remote.api.AuthApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.data.remote.model.mapper.toDto
import project.collab.banksampah.domain.model.LoginInfo
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.model.response.auth.LoginResponse
import project.collab.banksampah.domain.model.response.auth.RegisterResponse
import project.collab.banksampah.domain.repository.AuthRepository
import project.collab.banksampah.domain.utils.ResponseResult

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {
    override suspend fun registerUser(userData: RegisterRequest): ResponseResult<RegisterResponse> {
        val request = userData.toDto()
        val result = authApiService.registerUser(request)

        return when (result) {
            is ResponseResult.Success -> {
                ResponseResult.Success(result.data.toDomain())
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun loginUser(userLogin: LoginRequest): ResponseResult<LoginResponse> {
        val request = userLogin.toDto()
        val result = authApiService.loginUser(request)

        return when (result) {
            is ResponseResult.Success -> {
                val userLoginInfo = LoginInfo(
                    phoneNumber = userLogin.phoneNumber,
                    token = result.data.token ?: "",
                    userId = result.data.id ?: ""
                )

                tokenDataSource.saveUserLoginData(data = userLoginInfo)
                ResponseResult.Success(result.data.toDomain())
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun logout() {
        tokenDataSource.clearUserLoginData()
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return tokenDataSource.isLoggedIn()
    }

}