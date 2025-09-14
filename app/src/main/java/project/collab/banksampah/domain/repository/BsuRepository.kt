package project.collab.banksampah.domain.repository

import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.domain.utils.ResponseResult

interface BsuRepository {
    suspend fun getBsuList(
        page: Int,
        limit: Int,
        bsuBranchName: String? = null
    ): ResponseResult<List<Bsu>>

    suspend fun getBsuById(id: String): ResponseResult<Bsu>
}