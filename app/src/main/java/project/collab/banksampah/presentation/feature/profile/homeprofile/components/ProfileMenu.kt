package project.collab.banksampah.presentation.feature.profile.homeprofile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.theme.Spacing_20

@Composable
fun ProfileMenu() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing_20),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            BaseTitleSection(title = "Menu")
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