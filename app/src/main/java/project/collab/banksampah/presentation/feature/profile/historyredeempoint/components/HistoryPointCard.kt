package project.collab.banksampah.presentation.feature.profile.historyredeempoint.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointData
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_40
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun HistoryPointCard(
    historyRedeemPointData: HistoryRedeemPointData,
    onClick: () -> Unit
) {
    BaseCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = Spacing_30,
                    horizontal = Spacing_12
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing_4)
            ) {
                Text(
                    text = "Status Penukaran",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                HistoryPointChipStatus(status = historyRedeemPointData.redeemStatus)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Permintaan Poin",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.size(Spacing_10))

                    Text(
                        text = historyRedeemPointData.pointRequest,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseImage(
                    image = R.drawable.ic_point_second,
                    modifier = Modifier.width(Size_40).height(Size_20)
                    
                )

                Spacer(modifier = Modifier.size(Spacing_4))

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "",
                    tint = PrimaryGreen
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryPointCard() {
    HistoryPointCard(
        historyRedeemPointData = HistoryRedeemPointData(
            pointRequest = "20.000",
            RedeemStatus.DENIED
        ),
        onClick = {}
    )
}