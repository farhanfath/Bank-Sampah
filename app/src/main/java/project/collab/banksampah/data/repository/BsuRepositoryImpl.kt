package project.collab.banksampah.data.repository

import project.collab.banksampah.data.remote.api.BsuApiService
import project.collab.banksampah.data.remote.model.mapper.toDomain
import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.domain.repository.BsuRepository
import project.collab.banksampah.domain.utils.ResponseResult

class BsuRepositoryImpl (
    private val apiService: BsuApiService
) : BsuRepository {
    override suspend fun getBsuList(
        page: Int,
        limit: Int,
        bsuBranchName: String?
    ): ResponseResult<List<Bsu>> {
        val result = apiService.getBsuList(page, limit, bsuBranchName)

        return when(result) {
            is ResponseResult.Success -> {
                val bsu = result.data.data.bsu.map { it.toDomain() }
                ResponseResult.Success(bsu)
            }
            is ResponseResult.Error -> result
        }
    }

    override suspend fun getBsuById(id: String): ResponseResult<Bsu> {
        val result = apiService.getBsuById(id)

        return when(result) {
            is ResponseResult.Success -> {
                val bsuDetail = result.data.data.toDomain()
                ResponseResult.Success(bsuDetail)
            }
            is ResponseResult.Error -> result
        }
    }

}