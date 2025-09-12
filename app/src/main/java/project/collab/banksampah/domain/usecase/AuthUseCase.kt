package project.collab.banksampah.domain.usecase

import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.model.response.auth.LoginResponse
import project.collab.banksampah.domain.model.response.auth.RegisterResponse
import project.collab.banksampah.domain.repository.AuthRepository
import project.collab.banksampah.domain.utils.ResponseResult
import project.collab.banksampah.domain.utils.isValidPhoneNumber

interface AuthUseCase {
    suspend fun userRegister(userRegister: RegisterRequest): ResponseResult<RegisterResponse>
    suspend fun userLogin(userLogin: LoginRequest): ResponseResult<LoginResponse>
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
}

class AuthUseCaseImpl (
    private val authRepository: AuthRepository
) : AuthUseCase {
    override suspend fun userRegister(userRegister: RegisterRequest): ResponseResult<RegisterResponse> =
        authRepository.registerUser(userData = userRegister)

    override suspend fun userLogin(userLogin: LoginRequest): ResponseResult<LoginResponse> =
        authRepository.loginUser(userLogin = userLogin)

    override suspend fun logout() {
        authRepository.logout()
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return authRepository.isLoggedIn()
    }

}