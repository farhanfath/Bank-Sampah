package project.collab.banksampah

import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.state.RedeemTrashHistoryData
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.state.RedeemTrashHistoryResponse
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointData
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointResponse
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus

// deleted soon

fun getHistoryRedeemPointResponse() : HistoryRedeemPointResponse {
    return HistoryRedeemPointResponse(
        historyRedeemPointData = listOf(
            HistoryRedeemPointData(
                pointRequest = "20.000",
                RedeemStatus.DENIED
            ),
            HistoryRedeemPointData(
                pointRequest = "20.000",
                RedeemStatus.PENDING
            ),
            HistoryRedeemPointData(
                pointRequest = "20.000",
                RedeemStatus.SUCCESS
            )
        )
    )
}

fun getRedeemTrashHistoryResponse() : RedeemTrashHistoryResponse {
    return RedeemTrashHistoryResponse(
        listData = listOf(
            RedeemTrashHistoryData(
                date = "10/02/20",
                totalPoint = "10.000"
            ),
            RedeemTrashHistoryData(
                date = "10/03/24",
                totalPoint = "30.000"
            ),
            RedeemTrashHistoryData(
                date = "26/10/25",
                totalPoint = "100.000"
            ),
        )
    )
}