package project.collab.banksampah.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun UniversalLoadingScreen(
    modifier: Modifier = Modifier,
    loadingText: String = "Loading...",
    showText: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor: Color = MaterialTheme.colorScheme.secondary,
    loadingType: LoadingType = LoadingType.PULSING_DOTS
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Loading Animation
            when (loadingType) {
                LoadingType.PULSING_DOTS -> PulsingDotsLoader(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor
                )
                LoadingType.ROTATING_CIRCLES -> RotatingCirclesLoader(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor
                )
                LoadingType.WAVE_LOADER -> WaveLoader(
                    primaryColor = primaryColor
                )
                LoadingType.BOUNCING_BALLS -> BouncingBallsLoader(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor
                )
            }

            // Loading Text
            if (showText) {
                Spacer(modifier = Modifier.height(32.dp))

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(
                        animationSpec = tween(1000, delayMillis = 500)
                    )
                ) {
                    Text(
                        text = loadingText,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun PulsingDotsLoader(
    primaryColor: Color,
    secondaryColor: Color
) {
    val dots = 3
    val animationDelay = 150

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dots) { index ->
            val animationSpec = remember {
                infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animationDelay * dots
                        0f at (animationDelay * index) using LinearOutSlowInEasing
                        1f at (animationDelay * (index + 1)) using LinearOutSlowInEasing
                        0f at (animationDelay * dots) using LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            }

            val scale by animateFloatAsState(
                targetValue = 1f,
                animationSpec = animationSpec,
                label = "dotScale"
            )

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(primaryColor, secondaryColor)
                        )
                    )
            )
        }
    }
}

@Composable
private fun RotatingCirclesLoader(
    primaryColor: Color,
    secondaryColor: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .rotate(rotation)
            .scale(scale),
        contentAlignment = Alignment.Center
    ) {
        // Outer circle
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            primaryColor.copy(alpha = 0.8f),
                            primaryColor.copy(alpha = 0.2f),
                            primaryColor.copy(alpha = 0.8f)
                        )
                    )
                )
        )

        // Inner circle
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(secondaryColor.copy(alpha = 0.6f))
        )
    }
}

@Composable
private fun WaveLoader(primaryColor: Color) {
    val waves = 4

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(waves) { index ->

            val height by animateFloatAsState(
                targetValue = 1f,
                label = "waveHeight"
            )

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height((20 * height + 10).dp)
                    .clip(CircleShape)
                    .background(primaryColor)
            )
        }
    }
}

@Composable
private fun BouncingBallsLoader(
    primaryColor: Color,
    secondaryColor: Color
) {
    val balls = 3

    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(balls) { index ->
            val offsetY by animateFloatAsState(
                targetValue = -20f,
                label = "bounceOffset"
            )

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .offset(y = offsetY.dp)
                    .clip(CircleShape)
                    .background(
                        if (index % 2 == 0) primaryColor else secondaryColor
                    )
            )
        }
    }
}

enum class LoadingType {
    PULSING_DOTS,
    ROTATING_CIRCLES,
    WAVE_LOADER,
    BOUNCING_BALLS
}

// Usage Examples
@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    MaterialTheme {
        UniversalLoadingScreen(
            loadingText = "Memuat data...",
            loadingType = LoadingType.PULSING_DOTS
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenRotatingPreview() {
    MaterialTheme {
        UniversalLoadingScreen(
            loadingText = "Menyimpan...",
            loadingType = LoadingType.ROTATING_CIRCLES,
            backgroundColor = Color(0xFF1A1A1A),
            primaryColor = Color(0xFF6200EA),
            secondaryColor = Color(0xFF03DAC6)
        )
    }
}

@Composable
fun LoadingOverlay(
    isVisible: Boolean,
    loadingText: String = "Loading...",
    loadingType: LoadingType = LoadingType.PULSING_DOTS,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            UniversalLoadingScreen(
                loadingText = loadingText,
                loadingType = loadingType,
                backgroundColor = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}