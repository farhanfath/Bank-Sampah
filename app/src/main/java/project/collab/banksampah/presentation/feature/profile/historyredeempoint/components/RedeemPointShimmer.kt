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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.PrimaryGrey
import project.collab.banksampah.presentation.theme.Size_1
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun RedeemPointCardShimmer() {
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
                    vertical = 30.dp,
                    horizontal = 12.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Title shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(120.dp)
                        .height(20.dp)
                )

                // Status chip shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(100.dp)
                        .height(24.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Label shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(90.dp)
                            .height(16.dp)
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    // Value shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(40.dp)
                            .height(14.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                )

                Spacer(modifier = Modifier.size(4.dp))

                // Arrow shimmer
                BaseShimmer(
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}