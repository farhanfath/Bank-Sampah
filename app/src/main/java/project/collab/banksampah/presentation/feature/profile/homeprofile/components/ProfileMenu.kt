package project.collab.banksampah.presentation.feature.profile.homeprofile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.R
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Size_3
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun ProfileMenu() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing_20),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            HorizontalDivider(
                modifier = Modifier.width(Size_100),
                thickness = Size_3,
                color = PrimaryGreen
            )

            Spacer(modifier = Modifier.size(Spacing_4))
        }

        item {
            ProfileCard(
                title = "Penukaran Poin",
                description = "Ayo tukarkan poin mu sekarang juga!",
                icon = R.drawable.ic_point_redeem,
                onClick = {}
            )
        }

        item {
            ProfileCard(
                title = "Riawayat Penukaran Poin",
                icon = R.drawable.ic_point_history,
                onClick = {}
            )
        }

        item {
            ProfileCard(
                title = "Riawayat Penukaran Sampah",
                icon = R.drawable.ic_trash_redeem,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileMenu() {
    ProfileMenu()
}