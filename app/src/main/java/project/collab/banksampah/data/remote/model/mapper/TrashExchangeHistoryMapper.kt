package project.collab.banksampah.data.remote.model.mapper

import project.collab.banksampah.data.remote.model.response.trash_exchange.ListOfTrashItemDto
import project.collab.banksampah.data.remote.model.response.trash_exchange.TrashExchangeHistoryItemDto
import project.collab.banksampah.data.remote.model.response.trash_exchange.TrashExchangeHistoryListResponseDto
import project.collab.banksampah.domain.model.response.trash_exchange.ListOfTrash
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistory
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistoryListResult
import project.collab.banksampah.presentation.utils.replaceIfNull

fun TrashExchangeHistoryItemDto.toDomain() : TrashExchangeHistory {
    return TrashExchangeHistory(
        id = id.replaceIfNull(),
        bsuBranchName = bsuBranchName.replaceIfNull(),
        exchangeCode = exchangeCode.replaceIfNull(),
        exchangeDate = exchangeDate.replaceIfNull(),
        kaderName = kaderName.replaceIfNull(),
        totalPoint = totalPoint.replaceIfNull(),
        listOfTrash = listOfTrash.map { it.toDomain() }
    )
}

fun ListOfTrashItemDto.toDomain() : ListOfTrash {
    return ListOfTrash(
        id = id.replaceIfNull(),
        amount = amount.replaceIfNull(),
        totalPrice = totalPrice.replaceIfNull(),
        trashType = trashType.replaceIfNull(),
        unit = unit.replaceIfNull(),
        unitPrice = unitPrice.replaceIfNull()
    )
}

fun TrashExchangeHistoryListResponseDto.toDomain() : TrashExchangeHistoryListResult {
    return TrashExchangeHistoryListResult(
        histories = this.data.histories.map { it.toDomain() },
        pagination = this.data.pagination.toDomain()
    )
}