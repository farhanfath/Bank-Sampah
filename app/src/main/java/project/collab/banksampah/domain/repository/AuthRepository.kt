package project.collab.banksampah.domain.repository

import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.response.auth.LoginResponse
import project.collab.banksampah.domain.model.response.auth.RegisterResponse
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.utils.ResponseResult

interface AuthRepository {
    suspend fun registerUser(userData: RegisterRequest): ResponseResult<RegisterResponse>
    suspend fun loginUser(userLogin: LoginRequest): ResponseResult<LoginResponse>
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
}