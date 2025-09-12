package project.collab.banksampah.data.repository

import project.collab.banksampah.data.local.TokenDataSource
import project.collab.banksampah.data.remote.api.UserApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.repository.UserRepository
import project.collab.banksampah.domain.utils.ResponseResult

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val tokenDataSource: TokenDataSource
) : UserRepository {
    override suspend fun getUserById(): ResponseResult<User> {
        val userId = tokenDataSource.getUserId() ?: ""

        val result = userApiService.getUserById(userId)

        return when(result) {
            is ResponseResult.Success -> {
                ResponseResult.Success(data = result.data.data.toDomain())
            }
            is ResponseResult.Error -> result
        }
    }

}