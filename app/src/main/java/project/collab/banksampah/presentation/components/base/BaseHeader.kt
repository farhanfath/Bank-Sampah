package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
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
import androidx.compose.ui.text.style.TextAlign
import project.collab.banksampah.presentation.theme.Font_32
import project.collab.banksampah.presentation.theme.PrimaryBlack
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_30

@Composable
fun BaseHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Spacer(modifier = Modifier.size(Spacing_30))
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.align(
                alignment = Alignment.CenterStart
            ),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = PrimaryGreen,
                contentColor = PrimaryBlack
            ),
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Back"
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
        )
    }

    Spacer(modifier = modifier.size(Spacing_30))
}