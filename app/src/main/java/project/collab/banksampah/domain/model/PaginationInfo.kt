package project.collab.banksampah.domain.model


data class PaginationInfo(
    val currentPage: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val hasNext: Boolean,
    val hasPrev: Boolean
)