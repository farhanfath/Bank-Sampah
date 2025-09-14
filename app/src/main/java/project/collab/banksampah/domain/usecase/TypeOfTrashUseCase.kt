package project.collab.banksampah.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import project.collab.banksampah.data.paging.GenericPagingSource
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.repository.TypeOfTrashRepository
import project.collab.banksampah.domain.utils.ResponseResult

interface TypeOfTrashUseCase {
    fun getTypeOfTrashList(trashType: String? = null) : Flow<PagingData<TypeOfTrash>>
    suspend fun getTypeOfTrashById(id: String): ResponseResult<TypeOfTrash>
}

class TypeOfTrashUseCaseImpl(
    private val typeOfTrashRepository: TypeOfTrashRepository
) : TypeOfTrashUseCase {
    override fun getTypeOfTrashList(trashType: String?): Flow<PagingData<TypeOfTrash>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                GenericPagingSource { page, pageSize ->
                    typeOfTrashRepository.getTypeOfTrashList(page, pageSize, trashType)
                }
            }
        ).flow
    }

    override suspend fun getTypeOfTrashById(id: String): ResponseResult<TypeOfTrash> =
        typeOfTrashRepository.getTypeOfTrashById(id)

}