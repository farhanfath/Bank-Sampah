package project.collab.banksampah.presentation.feature.profile.user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_30
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_50

@Composable
fun ContactUsSection() {
    Card(
        modifier = Modifier
            .padding(horizontal = Spacing_50)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryGreen
        )
    ) {
        Row(
            modifier = Modifier
                .padding(Spacing_20)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BaseImage(
                modifier = Modifier.size(Size_30),
                image = R.drawable.ic_cs
            )

            Spacer(modifier = Modifier.size(Spacing_20))
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing_4),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Customer Service",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Text(
                    text = "Whatsapp Kami",
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.Underline,
                        color = Color.White
                    )
                )
            }
        }
    }
}