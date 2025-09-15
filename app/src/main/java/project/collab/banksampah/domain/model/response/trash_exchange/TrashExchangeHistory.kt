package project.collab.banksampah.domain.model.response.trash_exchange

import project.collab.banksampah.domain.model.PaginationInfo

data class TrashExchangeHistoryListResult(
    val histories : List<TrashExchangeHistory>,
    val pagination: PaginationInfo
)

data class TrashExchangeHistory(
    val id: String,
    val bsuBranchName: String,
    val exchangeCode: String,
    val exchangeDate: String,
    val kaderName: String,
    val totalPoint: Int,
    val listOfTrash: List<ListOfTrash>
)

data class ListOfTrash(
    val id: String,
    val amount: Int,
    val totalPrice: Int,
    val trashType: String,
    val unit: String,
    val unitPrice: Int
)