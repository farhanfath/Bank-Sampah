package project.collab.banksampah.presentation.feature.profile.user.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.BaseDialog
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.PrimaryRed
import project.collab.banksampah.presentation.theme.Size_1
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_50

@Composable
fun LogoutDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onLogoutClick: () -> Unit
) {
    BaseDialog(
        isVisible = isVisible,
        onDismiss = onDismiss
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(width = Size_1, color = PrimaryGreen)
        ) {
            Column(
                modifier = Modifier.padding(Spacing_50)
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Anda ingin keluar dari akun anda?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.padding(Spacing_20))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BaseButton(
                        text = "Batal",
                        textStyle = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        shape = RoundedCornerShape(Spacing_20),
                        containerColor = PrimaryRed,
                        onClick = onDismiss
                    )

                    BaseButton(
                        text = "Keluar",
                        textStyle = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        shape = RoundedCornerShape(Spacing_20),
                        onClick = onLogoutClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLogoutDialog() {
    LogoutDialog(
        isVisible = true,
        onDismiss = {},
        onLogoutClick = {}
    )
}