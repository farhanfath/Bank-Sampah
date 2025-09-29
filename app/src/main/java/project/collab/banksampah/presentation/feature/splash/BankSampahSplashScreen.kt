package project.collab.banksampah.presentation.feature.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

// Data class untuk color theme
data class BankSampahColors(
    val primaryGreen: Color = Color(0xFF4CAF50),
    val lightGreen: Color = Color(0xFF8BC34A),
    val darkGreen: Color = Color(0xFF2E7D32),
    val accentBlue: Color = Color(0xFF2196F3),
    val backgroundStart: Color = Color(0xFFF8FFF8),
    val backgroundEnd: Color = Color(0xFFE8F5E8)
)

// Animation state management
@Composable
private fun rememberSplashAnimationState(): SplashAnimationState {
    var logoVisible by remember { mutableStateOf(false) }
    var titleVisible by remember { mutableStateOf(false) }
    var subtitleVisible by remember { mutableStateOf(false) }
    var recycleAnimationActive by remember { mutableStateOf(false) }
    var loadingVisible by remember { mutableStateOf(false) }

    return SplashAnimationState(
        logoVisible = logoVisible,
        titleVisible = titleVisible,
        subtitleVisible = subtitleVisible,
        recycleAnimationActive = recycleAnimationActive,
        loadingVisible = loadingVisible,
        setLogoVisible = { logoVisible = it },
        setTitleVisible = { titleVisible = it },
        setSubtitleVisible = { subtitleVisible = it },
        setRecycleAnimationActive = { recycleAnimationActive = it },
        setLoadingVisible = { loadingVisible = it }
    )
}

data class SplashAnimationState(
    val logoVisible: Boolean,
    val titleVisible: Boolean,
    val subtitleVisible: Boolean,
    val recycleAnimationActive: Boolean,
    val loadingVisible: Boolean,
    val setLogoVisible: (Boolean) -> Unit,
    val setTitleVisible: (Boolean) -> Unit,
    val setSubtitleVisible: (Boolean) -> Unit,
    val setRecycleAnimationActive: (Boolean) -> Unit,
    val setLoadingVisible: (Boolean) -> Unit
)

@Composable
fun BankSampahSplashScreen(
    onSplashComplete: () -> Unit = {},
    colors: BankSampahColors = BankSampahColors()
) {
    val animationState = rememberSplashAnimationState()

    // Animation timing controller
    LaunchedEffect(Unit) {
        delay(300)
        animationState.setLogoVisible(true)

        delay(600)
        animationState.setRecycleAnimationActive(true)

        delay(800)
        animationState.setTitleVisible(true)

        delay(400)
        animationState.setSubtitleVisible(true)

        delay(200)
        animationState.setLoadingVisible(true)

        delay(2000)
        onSplashComplete()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colors.backgroundStart
    ) {
        SplashBackground(colors = colors) {
            SplashContent(
                animationState = animationState,
                colors = colors
            )
        }
    }
}

@Composable
private fun SplashBackground(
    colors: BankSampahColors,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        colors.backgroundStart,
                        colors.lightGreen.copy(alpha = 0.05f),
                        colors.backgroundEnd.copy(alpha = 0.1f)
                    ),
                    radius = 1000f
                )
            )
    ) {
        content()
    }
}

@Composable
private fun SplashContent(
    animationState: SplashAnimationState,
    colors: BankSampahColors
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Logo Section
        AnimatedLogo(
            visible = animationState.logoVisible,
            animationActive = animationState.recycleAnimationActive,
            colors = colors
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Title Section
        AnimatedTitle(
            visible = animationState.titleVisible,
            colors = colors
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle Section
        AnimatedSubtitle(
            visible = animationState.subtitleVisible,
            colors = colors
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Loading Section
        AnimatedLoadingIndicator(
            visible = animationState.loadingVisible,
            colors = colors
        )
    }
}

@Composable
private fun AnimatedLogo(
    visible: Boolean,
    animationActive: Boolean,
    colors: BankSampahColors
) {
    val pulse by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val rotation by rememberInfiniteTransition(label = "rotation").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation_angle"
    )

    val innerRotation by rememberInfiniteTransition(label = "inner_rotation").animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "inner_rotation_angle"
    )

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) + fadeIn(animationSpec = tween(800))
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .scale(if (animationActive) pulse else 1f),
            contentAlignment = Alignment.Center
        ) {
            // Outer gradient ring
            Canvas(modifier = Modifier.size(130.dp)) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            colors.primaryGreen.copy(alpha = 0.12f),
                            colors.lightGreen.copy(alpha = 0.06f),
                            Color.Transparent
                        )
                    ),
                    radius = size.minDimension / 2f
                )
            }

            // Inner gradient ring
            Canvas(modifier = Modifier.size(100.dp)) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            colors.lightGreen.copy(alpha = 0.08f),
                            Color.Transparent
                        )
                    ),
                    radius = size.minDimension / 2f
                )
            }

            // Main Simple Arrow Circle
            Canvas(
                modifier = Modifier.size(90.dp)
            ) {
                drawSimpleArrowCircle(
                    color = colors.primaryGreen,
                    accentColor = colors.lightGreen,
                    rotation = if (animationActive) rotation else 0f
                )
            }

            // Inner rotating accent
            Canvas(
                modifier = Modifier.size(35.dp)
            ) {
                drawInnerAccent(
                    color = colors.lightGreen,
                    rotation = if (animationActive) innerRotation else 0f
                )
            }

            // Floating leaf accents
            Canvas(
                modifier = Modifier
                    .size(18.dp)
                    .offset(x = 50.dp, y = (-50).dp)
            ) {
                drawLeaf(colors.lightGreen)
            }

            Canvas(
                modifier = Modifier
                    .size(14.dp)
                    .offset(x = (-52).dp, y = 48.dp)
            ) {
                drawLeaf(colors.primaryGreen.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
private fun AnimatedTitle(
    visible: Boolean,
    colors: BankSampahColors
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMedium
            )
        ) + fadeIn(animationSpec = tween(600))
    ) {
        Text(
            text = "Bank Sampah",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = colors.darkGreen,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
private fun AnimatedSubtitle(
    visible: Boolean,
    colors: BankSampahColors
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(800, delayMillis = 100)
        ) + slideInVertically(
            animationSpec = tween(600, delayMillis = 100)
        )
    ) {
        Text(
            text = "Bank Sampah Kita Semua",
            fontSize = 16.sp,
            color = colors.primaryGreen.copy(alpha = 0.8f),
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.8.sp
        )
    }
}

@Composable
private fun AnimatedLoadingIndicator(
    visible: Boolean,
    colors: BankSampahColors
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(600, delayMillis = 300)
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                val infiniteTransition = rememberInfiniteTransition(label = "loading_$index")
                val animatedAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(600, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset(index * 200)
                    ),
                    label = "dot_alpha_$index"
                )

                Surface(
                    modifier = Modifier
                        .size(6.dp)
                        .alpha(animatedAlpha),
                    shape = CircleShape,
                    color = colors.primaryGreen
                ) {}
            }
        }
    }
}

// Utility functions for drawing
private fun DrawScope.drawSimpleArrowCircle(
    color: Color,
    accentColor: Color,
    rotation: Float = 0f
) {
    val radius = size.minDimension / 3.2f
    val center = Offset(size.width / 2f, size.height / 2f)
    val strokeWidth = 4.dp.toPx()

    rotate(rotation, center) {
        repeat(3) { i ->
            val baseAngle = i * 120f

            // Create circular arc for each arrow
            val startAngle = Math.toRadians((baseAngle - 25f).toDouble()).toFloat()
            val endAngle = Math.toRadians((baseAngle + 25f).toDouble()).toFloat()

            // Arc path points
            val startX = center.x + radius * cos(startAngle)
            val startY = center.y + radius * sin(startAngle)
            val endX = center.x + radius * cos(endAngle)
            val endY = center.y + radius * sin(endAngle)

            // Draw curved arrow body
            drawLine(
                color = color,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )

            // Draw arrow head at the end
            val arrowHeadSize = strokeWidth * 1.5f
            val arrowAngle1 = endAngle + 0.5f
            val arrowAngle2 = endAngle + 0.8f

            val arrow1X = endX + arrowHeadSize * cos(arrowAngle1)
            val arrow1Y = endY + arrowHeadSize * sin(arrowAngle1)
            val arrow2X = endX + arrowHeadSize * cos(arrowAngle2)
            val arrow2Y = endY + arrowHeadSize * sin(arrowAngle2)

            // Arrow head lines
            drawLine(
                color = color,
                start = Offset(endX, endY),
                end = Offset(arrow1X, arrow1Y),
                strokeWidth = strokeWidth * 0.8f,
                cap = StrokeCap.Round
            )

            drawLine(
                color = color,
                start = Offset(endX, endY),
                end = Offset(arrow2X, arrow2Y),
                strokeWidth = strokeWidth * 0.8f,
                cap = StrokeCap.Round
            )

            // Add subtle accent arc
            val innerRadius = radius * 0.85f
            val accentStartX = center.x + innerRadius * cos(startAngle)
            val accentStartY = center.y + innerRadius * sin(startAngle)
            val accentEndX = center.x + innerRadius * cos(endAngle)
            val accentEndY = center.y + innerRadius * sin(endAngle)

            drawLine(
                color = accentColor.copy(alpha = 0.5f),
                start = Offset(accentStartX, accentStartY),
                end = Offset(accentEndX, accentEndY),
                strokeWidth = strokeWidth * 0.4f,
                cap = StrokeCap.Round
            )
        }
    }

    // Center circle
    drawCircle(
        color = color.copy(alpha = 0.1f),
        radius = radius * 0.2f,
        center = center
    )

    drawCircle(
        color = color,
        radius = radius * 0.2f,
        center = center,
        style = Stroke(width = 2.dp.toPx())
    )
}

private fun DrawScope.drawInnerAccent(
    color: Color,
    rotation: Float = 0f
) {
    val center = Offset(size.width / 2f, size.height / 2f)
    val radius = size.minDimension / 6f

    rotate(rotation, center) {
        repeat(4) { i ->
            val angle = i * 90f
            val radian = Math.toRadians(angle.toDouble()).toFloat()

            val startX = center.x + radius * 0.3f * cos(radian)
            val startY = center.y + radius * 0.3f * sin(radian)
            val endX = center.x + radius * cos(radian)
            val endY = center.y + radius * sin(radian)

            drawLine(
                color = color.copy(alpha = 0.4f),
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 1.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

private fun DrawScope.drawLeaf(color: Color) {
    val path = Path().apply {
        moveTo(size.width * 0.2f, size.height * 0.8f)
        quadraticBezierTo(
            size.width * 0.05f, size.height * 0.5f,
            size.width * 0.5f, size.height * 0.1f
        )
        quadraticBezierTo(
            size.width * 0.95f, size.height * 0.5f,
            size.width * 0.8f, size.height * 0.8f
        )
        quadraticBezierTo(
            size.width * 0.5f, size.height * 0.95f,
            size.width * 0.2f, size.height * 0.8f
        )
    }

    // Fill leaf
    drawPath(
        path = path,
        color = color.copy(alpha = 0.3f)
    )

    // Leaf outline
    drawPath(
        path = path,
        color = color,
        style = Stroke(
            width = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    )

    // Leaf vein
    drawLine(
        color = color,
        start = Offset(size.width * 0.5f, size.height * 0.15f),
        end = Offset(size.width * 0.5f, size.height * 0.75f),
        strokeWidth = 1.dp.toPx(),
        cap = StrokeCap.Round
    )
}
