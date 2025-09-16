package project.collab.banksampah.presentation.feature.profile.redeempoint.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.PrimaryGrey
import project.collab.banksampah.presentation.theme.Size_1
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16

@Composable
fun ColumnScope.TotalPointCardShimmer() {
    BaseCard(
        stroke = Size_1,
        strokeColor = PrimaryGrey,
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
            // Left Content Shimmer
            Column(
                modifier = Modifier.padding(end = Spacing_16),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // "Poin Anda :" text shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(80.dp)
                        .height(16.dp)
                )

                // Total point value shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(60.dp)
                        .height(14.dp)
                )
            }

            // Right Content Shimmer (Icon)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Point icon shimmer
                BaseShimmer(
                    modifier = Modifier.size(Size_30)
                )
            }
        }
    }
}