package project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.state

data class RedeemTrashHistoryResponse(
    val listData: List<RedeemTrashHistoryData>
)

data class RedeemTrashHistoryData(
    val date: String,
    val totalPoint: String
)