package project.collab.banksampah.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
    val total: Int,
    val limit: Int,
    val page: Int,
    val totalPages: Int
)