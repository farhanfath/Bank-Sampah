package project.collab.banksampah.presentation.feature.profile.user.components.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Size_40
import project.collab.banksampah.presentation.theme.Size_50
import project.collab.banksampah.presentation.theme.Size_80
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_50

@Composable
fun ProfileHeaderShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile card shimmer
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Size_2
            ),
            shape = RoundedCornerShape(Spacing_12)
        ) {
            Row(
                modifier = Modifier.padding(Spacing_10),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile icon shimmer - circular
                BaseShimmer(
                    modifier = Modifier
                        .size(Size_50)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.size(Spacing_4))

                Column {
                    // "Halo" text shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(Size_40)
                            .height(Spacing_16)
                    )

                    Spacer(modifier = Modifier.height(Spacing_4))

                    // Username shimmer
                    BaseShimmer(
                        modifier = Modifier
                            .width(Size_80)
                            .height(Spacing_16)
                    )
                }
            }
        }

        // Notification button shimmer
        BaseShimmer(
            modifier = Modifier
                .size(Spacing_50)
                .clip(CircleShape)
        )
    }
}