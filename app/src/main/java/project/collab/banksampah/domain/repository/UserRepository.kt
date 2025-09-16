package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.model.request.EditPassRequest
import project.collab.banksampah.domain.model.request.UserRequest
import project.collab.banksampah.domain.model.response.edit.EditPassResponse
import project.collab.banksampah.domain.model.response.edit.EditUserResponse
import project.collab.banksampah.domain.utils.ResponseResult

interface UserRepository {
    suspend fun getUserById(): ResponseResult<User>
    suspend fun patchEditUserData(editRequest: UserRequest) : ResponseResult<EditUserResponse>
    suspend fun postEditPassword(passRequest: EditPassRequest) : ResponseResult<EditPassResponse>
}