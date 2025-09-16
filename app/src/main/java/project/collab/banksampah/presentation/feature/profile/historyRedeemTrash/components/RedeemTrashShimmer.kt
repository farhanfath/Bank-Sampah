package project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_40
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun RedeemTrashCardShimmer() {
    BaseCard(
        stroke = Size_1,
        strokeColor = PrimaryGrey,
        modifier = Modifier
            .padding(vertical = Spacing_8, horizontal = Spacing_4)
            .fillMaxWidth()
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
            // Left Content Shimmer
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing_4)
            ) {
                // Title shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(120.dp)
                        .height(20.dp)
                )

                // Date shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(140.dp)
                        .height(16.dp)
                )

                // Points row shimmer
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing_10)
                ) {
                    // "Permintaan Poin" text shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(100.dp)
                            .height(16.dp)
                    )

                    // Points value shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(40.dp)
                            .height(14.dp)
                    )
                }
            }

            // Right Content Shimmer
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing_4)
            ) {
                // Trash icon shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(Size_40)
                        .height(Size_20)
                )

                // Chevron icon shimmer
                BaseShimmer(
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}