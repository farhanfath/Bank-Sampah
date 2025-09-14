package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.response.bsu.BsuDetailResponseDto
import project.collab.banksampah.data.remote.model.response.bsu.BsuListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface BsuApiService {
    suspend fun getBsuList(
        page: Int,
        limit: Int = 10,
        bsuBranchName: String?
    ): ResponseResult<BsuListResponseDto>

    suspend fun getBsuById(
        id: String
    ) : ResponseResult<BsuDetailResponseDto>
}

class BsuApiServiceImpl(
    private val httpClient: HttpClient
) : BsuApiService {
    override suspend fun getBsuList(
        page: Int,
        limit: Int,
        bsuBranchName: String?
    ): ResponseResult<BsuListResponseDto> {
        return safeApiCall {
            val response : BsuListResponseDto = httpClient.get(ApiEndpoint.BSU.ALL) {
                parameter("page", page)
                parameter("limit", limit)
                bsuBranchName?.let { parameter("bsuBranchName", it) }
            }.body()
            response
        }
    }

    override suspend fun getBsuById(id: String): ResponseResult<BsuDetailResponseDto> {
        return safeApiCall {
            val response : BsuDetailResponseDto = httpClient.get(
                ApiEndpoint.BSU.BY_ID.replace("{id}", id)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }

}