package project.collab.banksampah.presentation.feature.auth.register.components.bsu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Spacing_4

// Shimmer for dropdown items
@Composable
fun BsuDropdownItemShimmer() {
    DropdownMenuItem(
        text = {
            Column(
                modifier = Modifier
                    .padding(vertical = Spacing_4)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BaseShimmer(
                    modifier = Modifier
                        .width(150.dp)
                        .height(16.dp)
                )
                BaseShimmer(
                    modifier = Modifier
                        .width(100.dp)
                        .height(12.dp)
                )
                BaseShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
            }
        },
        onClick = { }
    )
}