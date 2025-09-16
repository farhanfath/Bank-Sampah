package project.collab.banksampah.presentation.feature.profile.homeprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Size_50
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun ColumnScope.ProfileHeaderError(
    onRetry: () -> Unit,
    errorMessage: String = "Gagal memuat profil"
) {
    Card(
        modifier = Modifier.align(
            alignment = Alignment.CenterHorizontally
        ),
        onClick = onRetry,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFF6B6B)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Size_2
        ),
        shape = RoundedCornerShape(Spacing_12)
    ) {
        Row(
            modifier = Modifier.padding(
                start = Spacing_10,
                top = Spacing_10,
                bottom = Spacing_10,
                end = Spacing_20
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(Size_50),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Error",
                tint = Color.White.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.size(Spacing_8))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Halo",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White.copy(alpha = 0.9f)
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Tap to retry",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}