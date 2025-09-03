package project.collab.banksampah.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import project.collab.banksampah.presentation.theme.Size_35

@Composable
fun CustomTopBar(
    additionalContent: @Composable () -> Unit = {}
) {
    Column {
        Box(
            modifier = Modifier
                .height(Size_35)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        )
        additionalContent()
    }
}