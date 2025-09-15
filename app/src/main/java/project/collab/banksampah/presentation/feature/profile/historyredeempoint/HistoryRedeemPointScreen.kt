package project.collab.banksampah.presentation.feature.profile.historyredeempoint

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.base.BaseScreenWithListItem
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.HistoryPointCard
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointData
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointResponse
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun HistoryRedeemPointScreen(
    onBackClick: () -> Unit,
    historyRedeemPointResponse: HistoryRedeemPointResponse
) {

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {
        BaseScreenWithListItem(
            modifier = Modifier
                .padding(horizontal = Spacing_4, vertical = it.calculateTopPadding())
                .fillMaxSize(),
            onBackClick = onBackClick,
            title = "Riwayat Penukaran Poin",
            subTitle = "Riwayat",
            itemList = historyRedeemPointResponse.historyRedeemPointData
        ) { redeemPointData ->
            HistoryPointCard(
                historyRedeemPointData = redeemPointData
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistoryRedeemPointScreen() {
    HistoryRedeemPointScreen(
        historyRedeemPointResponse = HistoryRedeemPointResponse(
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
        ),
        onBackClick = {}
    )
}