package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.article.ArticleItemDto
import project.collab.banksampah.data.remote.model.response.article.ArticleListResponseDto
import project.collab.banksampah.domain.model.PaginationInfo
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.model.response.article.ArticleListResult

fun ArticleItemDto.toDomain() : Article {
    return Article(
        id = id,
        title = articleTitle,
        coverImageFileName = photoCoverFileName,
        coverImageUrl = photoCoverFileURL,
        imageFileNames = images,
        imageUrls = imagesURL,
        description = description,
        editor = editor,
        timestamp = timeStamp
    )
}

fun ArticleListResponseDto.toDomain(): ArticleListResult {
    return ArticleListResult(
        articles = this.data.article.map { it.toDomain() },
        pagination = PaginationInfo(
            currentPage = this.data.pagination.page,
            perPage = this.data.pagination.limit,
            totalPages = this.data.pagination.totalPages,
            totalItems = this.data.pagination.total,
            hasNext = this.data.pagination.page < this.data.pagination.totalPages,
            hasPrev = this.data.pagination.page > 1
        )
    )
}