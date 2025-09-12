package project.collab.banksampah.presentation.feature.gallery.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import project.collab.banksampah.domain.model.response.gallery.Gallery
import project.collab.banksampah.presentation.components.base.BaseImage

@Composable
fun GalleryCard(
    item: Gallery,
    height: Dp = 220.dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)

    Box(
        modifier = modifier
            .height(height)
            .clip(shape)
            .clickable { onClick() }
    ) {
        BaseImage(
            image = item.fileURL,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}