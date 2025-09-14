package project.collab.banksampah.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import project.collab.banksampah.data.remote.model.response.schedule.ScheduleDetailResponseDto
import project.collab.banksampah.data.remote.model.response.schedule.ScheduleListResponseDto
import project.collab.banksampah.data.remote.utils.ApiEndpoint
import project.collab.banksampah.data.remote.utils.safeApiCall
import project.collab.banksampah.domain.utils.ResponseResult

interface ScheduleApiService {
    suspend fun getSchedulesList(
        page: Int,
        limit: Int = 10,
        bsuBranchName: String?
    ) : ResponseResult<ScheduleListResponseDto>

    suspend fun getScheduleById(
        id: String
    ) : ResponseResult<ScheduleDetailResponseDto>
}

class ScheduleApiServiceImpl(
    private val httpClient: HttpClient
) : ScheduleApiService {
    override suspend fun getSchedulesList(
        page: Int,
        limit: Int,
        bsuBranchName: String?
    ): ResponseResult<ScheduleListResponseDto> {
        return safeApiCall {
            val response : ScheduleListResponseDto = httpClient.get(ApiEndpoint.Schedule.ALL) {
                parameter("page", page)
                parameter("limit", limit)
                bsuBranchName?.let { parameter("bsuBranchName", it) }
            }.body()
            response
        }
    }

    override suspend fun getScheduleById(id: String): ResponseResult<ScheduleDetailResponseDto> {
        return safeApiCall {
            val response : ScheduleDetailResponseDto = httpClient.get(
                ApiEndpoint.Schedule.BY_ID.replace("{id}", id)
            ) {
                contentType(ContentType.Application.Json)
            }.body()
            response
        }
    }

}