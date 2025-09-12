package project.collab.banksampah.domain.usecase

import project.collab.banksampah.domain.model.User
import project.collab.banksampah.domain.repository.UserRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface UserUseCase {
    suspend fun getUserById() : ResponseResult<User>
}

class UserUseCaseImpl(
    private val userRepository: UserRepository
) : UserUseCase {
    override suspend fun getUserById(): ResponseResult<User> = userRepository.getUserById()

}