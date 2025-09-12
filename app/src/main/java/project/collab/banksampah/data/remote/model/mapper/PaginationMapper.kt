package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.PaginationDto
import project.collab.banksampah.domain.model.PaginationInfo

fun PaginationDto.toDomain() : PaginationInfo {
    return PaginationInfo(
        currentPage = page,
        perPage = limit,
        totalPages = totalPages,
        totalItems = total,
        hasNext = page < totalPages,
        hasPrev = page > 1
    )
}