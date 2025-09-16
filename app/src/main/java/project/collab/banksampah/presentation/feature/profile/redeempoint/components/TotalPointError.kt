package project.collab.banksampah.presentation.feature.profile.redeempoint.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun ColumnScope.TotalPointErrorCard(
    onRetry: () -> Unit,
    errorMessage: String = "Gagal memuat poin"
) {
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
                    text = "Poin Anda :",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFE53E3E) // Red color for error
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing_8)
            ) {
                // Error icon
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Error",
                    tint = Color(0xFFE53E3E),
                    modifier = Modifier.size(Size_20)
                )

                // Retry button
                IconButton(
                    onClick = onRetry,
                    modifier = Modifier.size(Size_30)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Retry",
                        tint = PrimaryGreen,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}