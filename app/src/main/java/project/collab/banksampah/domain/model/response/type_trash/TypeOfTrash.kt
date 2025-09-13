package project.collab.banksampah.domain.model.response.type_trash

import project.collab.banksampah.domain.model.PaginationInfo

data class TypeOfTrashListResult(
    val typeOfTrash: List<TypeOfTrash>,
    val pagination: PaginationInfo
)

data class TypeOfTrash(
    val id: String,
    val description: String,
    val editor: String,
    val fileName: String,
    val fileURL: String,
    val price: Int,
    val timeStamp: String,
    val trashType: String,
    val unit: String,
)