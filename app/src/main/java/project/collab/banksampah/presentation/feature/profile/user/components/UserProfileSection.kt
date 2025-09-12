package project.collab.banksampah.presentation.feature.profile.user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import project.collab.banksampah.R
import project.collab.banksampah.domain.model.User
import project.collab.banksampah.presentation.components.base.BaseCircleImage
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.PrimaryRed
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Size_12
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun UserProfileSection(
    userData: User,
    onLogoutClick: () -> Unit,
    onImageChangeClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box {
            BaseCircleImage(
                modifier = Modifier
                    .padding(Spacing_16)
                    .size(Size_100),
                image = R.drawable.placeholder_default
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = Spacing_16, bottom = Spacing_16)
                    .size(Size_20),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White
                ),
                onClick = onImageChangeClick
            ) {
                Icon(
                    modifier = Modifier.size(Size_12),
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "edit profile"
                )
            }
        }

        Spacer(modifier = Modifier.size(Spacing_10))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Spacing_4)
        ) {
            Text(
                text = userData.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "ID: ${userData.userId}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Cabang BSU: ${userData.bsuBranchCode}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        IconButton(
            modifier = Modifier
                .size(Size_30),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = PrimaryRed,
                contentColor = Color.White
            ),
            onClick = onLogoutClick
        ) {
            Icon(
                modifier = Modifier.size(Size_20),
                imageVector = Icons.AutoMirrored.Default.Logout,
                contentDescription = "logout"
            )
        }
    }
}