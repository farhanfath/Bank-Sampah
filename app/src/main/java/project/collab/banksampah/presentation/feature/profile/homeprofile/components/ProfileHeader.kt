package project.collab.banksampah.presentation.feature.profile.homeprofile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.feature.profile.user.UserUiState
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Size_50
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun ProfileHeader(
    userName: String,
    onUserProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            onClick = onUserProfileClick,
            colors = CardDefaults.cardColors(
                containerColor = PrimaryGreen
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Size_2
            ),
            shape = RoundedCornerShape(Spacing_12)
        ) {
            Row(
                modifier = Modifier.padding(start = Spacing_10, top = Spacing_10, bottom = Spacing_10, end = Spacing_30),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(Size_50),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.size(Spacing_4))
                Column {
                    Text(
                        text = "Halo",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Black
                        )
                    )
                }
            }
        }

        OutlinedIconButton(
            border = BorderStroke(width = Size_2, color = PrimaryGreen),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.NotificationsActive,
                contentDescription = "",
                tint = PrimaryGreen
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileHeader() {
    ProfileHeader(
        userName = "",
        onUserProfileClick = {}
    )
}