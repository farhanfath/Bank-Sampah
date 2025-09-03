package project.collab.banksampah.presentation.feature.profile.historyRedeemTrash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseScreenWithListItem
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.components.RedeemTrashCard
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.state.RedeemTrashHistoryData
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.state.RedeemTrashHistoryResponse

@Composable
fun RedeemTrashHistoryScreen(
    onBackClick: () -> Unit,
    redeemTrashHistoryResponse: RedeemTrashHistoryResponse
) {
    BaseScreenWithListItem(
        onBackClick = onBackClick,
        title = "Riwayat Penukaran Sampah",
        subTitle = "Riwayat",
        itemList = redeemTrashHistoryResponse.listData
    ) { trashData ->
        RedeemTrashCard(
            redeemTrashHistoryData = trashData,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RedeemTrashHistoryScreenPreview() {
    RedeemTrashHistoryScreen(
        onBackClick = {},
        redeemTrashHistoryResponse = RedeemTrashHistoryResponse(
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
    )
}