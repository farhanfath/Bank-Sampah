package project.collab.banksampah.presentation.feature.profile.user.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.*


@Composable
fun UserProfileSectionShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Profile image shimmer with camera icon
        Box {
            BaseShimmer(
                modifier = Modifier
                    .padding(Spacing_16)
                    .size(Size_100)
                    .clip(CircleShape) // Match BaseCircleImage shape
            )

            // Camera icon shimmer - matches position and size
            BaseShimmer(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = Spacing_16, bottom = Spacing_16)
                    .size(Size_20)
                    .clip(CircleShape) // Match IconButton shape
            )
        }

        Spacer(modifier = Modifier.size(Spacing_10))

        // User info column shimmer
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Spacing_4)
        ) {
            BaseShimmer(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(Spacing_16)
            )

            BaseShimmer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(Spacing_15) // bodySmall height
            )

            // Branch code shimmer - medium length line
            BaseShimmer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(Spacing_15) // bodySmall height
            )
        }

        // Logout button shimmer
        BaseShimmer(
            modifier = Modifier
                .size(Size_30)
                .clip(CircleShape) // Match IconButton shape
        )
    }
}