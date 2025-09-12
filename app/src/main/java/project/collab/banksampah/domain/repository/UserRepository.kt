package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.utils.ResponseResult

interface UserRepository {
    suspend fun getUserById(): ResponseResult<User>
}