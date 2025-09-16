package project.collab.banksampah.presentation.feature.profile.historyredeempoint.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.PrimaryGrey
import project.collab.banksampah.presentation.theme.PrimaryRed
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun RedeemPointChipStatus(
    status: RedeemStatus
) {
    Card(
        shape = RoundedCornerShape(Spacing_16),
        colors = CardDefaults.cardColors(
            containerColor = when (status) {
                RedeemStatus.DENIED -> PrimaryRed
                RedeemStatus.PENDING -> PrimaryGrey
                RedeemStatus.SUCCESS -> PrimaryGreen
            },
            contentColor = Color.White
        )
    ) {
        Text(
            text = status.desc,
            modifier = Modifier.padding(horizontal = Spacing_10, vertical = Spacing_4),
            style = MaterialTheme.typography.bodySmall
        )
    }
}