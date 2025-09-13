package project.collab.banksampah.data.repository

import project.collab.banksampah.data.remote.api.TypeOfTrashApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.repository.TypeOfTrashRepository
import project.collab.banksampah.domain.utils.ResponseResult

class TypeOfTrashRepositoryImpl (
    private val apiService: TypeOfTrashApiService
) : TypeOfTrashRepository {
    override suspend fun getTypeOfTrashList(
        page: Int,
        limit: Int,
        trashType: String?
    ): ResponseResult<List<TypeOfTrash>> {
        val result = apiService.getTypeOfTrashList(page, limit, trashType)

        return when(result) {
            is ResponseResult.Success -> {
                val typeOfTrashList = result.data.data.typeOfTrash.map { it.toDomain() }
                ResponseResult.Success(typeOfTrashList)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getTypeOfTrashById(id: String): ResponseResult<TypeOfTrash> {
        val result = apiService.getTypeOfTrashById(id)

        return when(result) {
            is ResponseResult.Success -> {
                val typeOfTrashDetail = result.data.data.toDomain()
                ResponseResult.Success(typeOfTrashDetail)
            }
            is ResponseResult.Error -> result
        }
    }

}