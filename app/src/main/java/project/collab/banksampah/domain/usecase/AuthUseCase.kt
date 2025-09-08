package project.collab.banksampah.domain.usecase

import kotlinx.coroutines.flow.Flow
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
    override suspend fun userRegister(userRegister: RegisterRequest): ResponseResult<RegisterResponse> {
        when {
            userRegister.name.isBlank() -> return ResponseResult.Error(Exception("Nama tidak boleh kosong"))
            userRegister.nik.isBlank() -> return ResponseResult.Error(Exception("NIK tidak boleh kosong"))
            userRegister.phoneNumber.isBlank() -> return ResponseResult.Error(Exception("Nomor telepon tidak boleh kosong"))
            userRegister.address.isBlank() -> return ResponseResult.Error(Exception("Alamat tidak boleh kosong"))
            userRegister.bsuBranchName.isBlank() -> return ResponseResult.Error(Exception("Cabang tidak boleh kosong"))
            userRegister.bsuBranchCode.isBlank() -> return ResponseResult.Error(Exception("Kode cabang tidak boleh kosong"))
            userRegister.password.isBlank() -> return ResponseResult.Error(Exception("Kata sandi tidak boleh kosong"))
            !isValidPhoneNumber(userRegister.phoneNumber) -> return ResponseResult.Error(Exception("Format nomor HP tidak valid"))
        }

        return authRepository.registerUser(userData = userRegister)
    }

    override suspend fun userLogin(userLogin: LoginRequest): ResponseResult<LoginResponse> {
        when {
            userLogin.phoneNumber.isBlank() -> return ResponseResult.Error(Exception("Nomor telepon tidak boleh kosong"))
            userLogin.password.isBlank() -> return ResponseResult.Error(Exception("Kata sandi tidak boleh kosong"))
            !isValidPhoneNumber(userLogin.phoneNumber) -> return ResponseResult.Error(Exception("Format nomor HP tidak valid"))
        }

        return authRepository.loginUser(userLogin = userLogin)
    }

    override suspend fun logout() {
        authRepository.logout()
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return authRepository.isLoggedIn()
    }

}