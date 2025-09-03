package project.collab.banksampah.presentation.feature.profile.redeempoint.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16

@Composable
fun ColumnScope.TotalPointCard() {
    BaseCard(
        modifier = Modifier.align(Alignment.End)
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = Spacing_12,
                horizontal = Spacing_12
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(end = Spacing_16)
            ) {
                Text(
                    text = "Poin Anda : ",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "100.000",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseImage(
                    modifier = Modifier.size(Size_30),
                    image = R.drawable.ic_point
                )
            }
        }
    }
}