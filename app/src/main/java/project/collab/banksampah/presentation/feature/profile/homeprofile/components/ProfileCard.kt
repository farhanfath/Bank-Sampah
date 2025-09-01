package project.collab.banksampah.presentation.feature.profile.homeprofile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import project.collab.banksampah.presentation.components.base.BaseCard
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_150
import project.collab.banksampah.presentation.theme.Size_220
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Size_75
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun ProfileCard(
    title: String,
    description: String = "",
    icon: Int,
    onClick: () -> Unit
) {
    BaseCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = Spacing_30,
                    horizontal = Spacing_12
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    modifier = Modifier.width(Size_220),
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )

                if (description.isNotEmpty()) {
                    Text(
                        modifier = Modifier.width(Size_150),
                        text = description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseImage(
                    modifier = Modifier
                        .width(Size_75)
                        .height(Size_30),
                    image = icon
                )

                Spacer(modifier = Modifier.size(Spacing_4))

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "",
                    tint = PrimaryGreen
                )
            }
        }
    }
}