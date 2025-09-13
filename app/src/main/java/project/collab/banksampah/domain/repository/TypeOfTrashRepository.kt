package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.utils.ResponseResult

interface TypeOfTrashRepository {
    suspend fun getTypeOfTrashList(
        page: Int,
        limit: Int,
        trashType: String? = null
    ): ResponseResult<List<TypeOfTrash>>

    suspend fun getTypeOfTrashById(id: String): ResponseResult<TypeOfTrash>
}