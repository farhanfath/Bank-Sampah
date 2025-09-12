package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.gallery.GalleryItemDto
import project.collab.banksampah.data.remote.model.response.gallery.GalleryListResponseDto
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.domain.model.response.gallery.GalleryListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun GalleryItemDto.toDomain() : Gallery {
    return Gallery(
        id = id.replaceIfNull(),
        imgTitle = imgTitle.replaceIfNull(),
        fileURL = fileURL.replaceIfNull(),
        description = description.replaceIfNull(),
        editor = editor.replaceIfNull(),
        timestamp = timeStamp.replaceIfNull()
    )
}

fun GalleryListResponseDto.toDomain() : GalleryListResult {
    return GalleryListResult(
        galleries = this.data.gallery.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()

    )
}