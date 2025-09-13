package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.type_trash.TypeOfTrashItemDto
import project.collab.banksampah.data.remote.model.response.type_trash.TypeOfTrashListResponseDto
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrashListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun TypeOfTrashItemDto.toDomain() : TypeOfTrash {
    return TypeOfTrash(
        id = id.replaceIfNull(),
        description = description.replaceIfNull(),
        editor = editor.replaceIfNull(),
        fileName = fileName.replaceIfNull(),
        fileURL = fileURL.replaceIfNull(),
        price = price.replaceIfNull(),
        timeStamp = timeStamp.replaceIfNull(),
        trashType = trashType.replaceIfNull(),
        unit = unit.replaceIfNull()
    )
}

fun TypeOfTrashListResponseDto.toDomain() : TypeOfTrashListResult {
    return TypeOfTrashListResult(
        typeOfTrash = this.data.typeOfTrash.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()
    )
}