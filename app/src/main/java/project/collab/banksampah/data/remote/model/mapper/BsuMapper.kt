package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.bsu.BsuItemDto
import project.collab.banksampah.data.remote.model.response.bsu.BsuListResponseDto
import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.domain.model.response.bsu.BsuListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun BsuItemDto.toDomain(): Bsu {
    return Bsu(
        id = id.replaceIfNull(),
        address = address.replaceIfNull(),
        bsuBranchCode = bsuBranchCode.replaceIfNull(),
        bsuBranchName = bsuBranchName.replaceIfNull(),
        editor = editor.replaceIfNull(),
        mapAddress = mapsAddress.replaceIfNull(),
        timestamp = timeStamp.replaceIfNull()
    )
}

fun BsuListResponseDto.toDomain() : BsuListResult {
    return BsuListResult(
        bsu = this.data.bsu.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()
    )
}