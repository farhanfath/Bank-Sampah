package project.collab.banksampah.presentation.feature.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import project.collab.banksampah.R
import project.collab.banksampah.presentation.theme.Font_30
import project.collab.banksampah.presentation.theme.Font_32
import project.collab.banksampah.presentation.theme.PrimaryBlack
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_150
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_30

@Composable
fun AuthHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Spacer(modifier = Modifier.size(Spacing_30))
    Box(
        modifier = Modifier
            .padding(horizontal = Spacing_10)
            .fillMaxWidth(),
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
            modifier = Modifier.width(Size_150),
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center,
                lineHeight = Font_30,
                color = PrimaryGreen,
                fontWeight = FontWeight.ExtraBold,
                fontSize = Font_32
            )
        )
    }

    Spacer(modifier = modifier.size(Spacing_30))
}