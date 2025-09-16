package project.collab.banksampah.presentation.feature.article_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import project.collab.banksampah.presentation.components.base.BaseImage

@Composable
fun BoxScope.BackDropImage(
    currentImageHeight: Dp,
    articleImageUrl: String
) {
    BaseImage(
        image = articleImageUrl,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(currentImageHeight)
            .align(Alignment.TopCenter)
    )
    // gradient color
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(currentImageHeight)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        MaterialTheme.colorScheme.surface
                    ),
                    startY = 0f,
                    endY = 1200f
                )
            )
            .align(Alignment.TopCenter)
    )
}