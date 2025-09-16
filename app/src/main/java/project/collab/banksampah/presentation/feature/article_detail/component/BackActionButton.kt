package project.collab.banksampah.presentation.feature.article_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import project.collab.banksampah.presentation.theme.Spacing_20

@Composable
fun BackActionButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = Spacing_20, horizontal = Spacing_20)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            modifier = modifier,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = {
                onBackClick()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = ""
            )
        }
    }
}