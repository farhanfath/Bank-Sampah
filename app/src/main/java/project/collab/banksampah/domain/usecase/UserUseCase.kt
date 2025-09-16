package project.collab.banksampah.domain.usecase

import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.request.EditPassRequest
import project.collab.banksampah.domain.model.request.UserRequest
import project.collab.banksampah.domain.model.response.edit.EditPassResponse
import project.collab.banksampah.domain.model.response.edit.EditUserResponse
import project.collab.banksampah.domain.repository.UserRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface UserUseCase {
    suspend fun getUserById() : ResponseResult<User>
    suspend fun patchEditUserData(editRequest: UserRequest) : ResponseResult<EditUserResponse>
    suspend fun postEditPassword(passEditRequest: EditPassRequest) : ResponseResult<EditPassResponse>
}

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {
    override suspend fun getUserById(): ResponseResult<User> = userRepository.getUserById()

    override suspend fun patchEditUserData(editRequest: UserRequest): ResponseResult<EditUserResponse> =
        userRepository.patchEditUserData(editRequest)

    override suspend fun postEditPassword(passEditRequest: EditPassRequest): ResponseResult<EditPassResponse> =
        userRepository.postEditPassword(passEditRequest)

}