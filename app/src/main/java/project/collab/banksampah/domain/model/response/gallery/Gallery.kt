package project.collab.banksampah.domain.model.response.gallery

import project.collab.banksampah.domain.model.PaginationInfo

data class GalleryListResult(
    val galleries: List<Gallery>,
    val pagination: PaginationInfo
)

data class Gallery(
    val id: String,
    val imgTitle: String,
    val fileURL: String,
    val description: String,
    val editor: String,
    val timestamp: String
)