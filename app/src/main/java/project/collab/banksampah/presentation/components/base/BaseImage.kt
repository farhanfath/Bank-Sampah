package project.collab.banksampah.presentation.components.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import project.collab.banksampah.R

@Composable
fun BaseImage(
    modifier: Modifier = Modifier,
    image: String,
    tintColor: Color? = null,
    contentScale: ContentScale = ContentScale.FillWidth,
    alpha: Float = 1f,
) {
    AsyncImage(
        model = image,
        contentDescription = "",
        modifier = modifier,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = if (tintColor == null) null else ColorFilter.tint(tintColor),
        placeholder = painterResource(R.drawable.placeholder_default),
        error = painterResource(R.drawable.placeholder_default)
    )
}

@Composable
fun BaseImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    tintColor: Color? = null,
    contentScale: ContentScale = ContentScale.FillWidth,
    alpha: Float = 1f,
    placeholderImage: Int = R.drawable.placeholder_default,
    errorImage: Int = R.drawable.placeholder_default
) {
    AsyncImage(
        model = image,
        contentDescription = "",
        modifier = modifier,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = if (tintColor == null) null else ColorFilter.tint(tintColor),
        placeholder = painterResource(placeholderImage),
        error = painterResource(errorImage)
    )
}

@Composable
fun BaseCircleImage(
    modifier: Modifier = Modifier,
    image: String,
    tintColor: Color? = null,
) {
    AsyncImage(
        model = image,
        contentDescription = "",
        modifier = modifier
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder_default),
        error = painterResource(R.drawable.placeholder_default)
    )
}

@Composable
fun BaseCircleImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    tintColor: Color? = null,
) {
    AsyncImage(
        model = image,
        contentDescription = "",
        modifier = modifier
            .clip(CircleShape),
        colorFilter = if (tintColor == null) null else ColorFilter.tint(tintColor),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder_default),
        error = painterResource(R.drawable.placeholder_default)
    )
}