package project.collab.banksampah.presentation.feature.profile.historyredeempoint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.HistoryPointCard
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointData
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointResponse
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun HistoryRedeemPointScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    historyRedeemPointResponse: HistoryRedeemPointResponse
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16, vertical = Spacing_4)
            .fillMaxSize(),
    ) {
        BaseHeader(
            title = "Riwayat Penukaran Poin",
            textStyle = MaterialTheme.typography.headlineSmall.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.size(Spacing_10))

        BaseTitleSection(
            title = "Riwayat"
        )

        Spacer(modifier = Modifier.size(Spacing_10))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing_10)
        ) {
            items(historyRedeemPointResponse.historyRedeemPointData) {
                HistoryPointCard(
                    historyRedeemPointData = it
                ) {

                }
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