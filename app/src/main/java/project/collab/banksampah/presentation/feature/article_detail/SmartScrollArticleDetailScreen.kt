package project.collab.banksampah.presentation.feature.article_detail

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.Spacing_50
import project.collab.banksampah.presentation.utils.toAnnotatedString
import project.collab.banksampah.presentation.utils.toFormattedDate

@Composable
fun SmartScrollArticleDetailScreen(
    onBackClick: () -> Unit,
    articleId: String,
    title: String,
    description: String,
    imageUrl: String,
    timeStamp: String
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val density = LocalDensity.current

    var cardOffset by remember { mutableFloatStateOf(0.4f) }
    val coroutineScope = rememberCoroutineScope()

    val isCardExpanded = cardOffset <= 0.2f
    val contentScrollState = rememberLazyListState()

    // Animated offset untuk smooth card movement
    val animatedCardOffset by animateFloatAsState(
        targetValue = cardOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "card_offset"
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image (Full Screen)
        BaseImage(
            image = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Dark overlay on image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.6f)
                        )
                    )
                )
        )

        IconButton(
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp)
                .size(40.dp)
                .zIndex(10f),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White.copy(alpha = 0.9f),
                contentColor = Color.Black
            ),
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Back"
            )
        }

        if (!isCardExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = screenHeight * 0.25f)
                    .zIndex(5f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 32.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Smart Draggable Content Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = screenHeight * animatedCardOffset)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            coroutineScope.launch {
                                // Smart snap logic
                                cardOffset = when {
                                    cardOffset < 0.15f -> 0.12f  // Expanded (tapi tidak sampai back button)
                                    cardOffset < 0.4f -> 0.12f   // Snap ke expanded
                                    else -> 0.4f                 // Snap ke default
                                }
                            }
                        }
                    ) { _, dragAmount ->
                        // Hanya bisa drag card jika content belum di-scroll atau scroll sudah di top
                        val canDragCard = !isCardExpanded ||
                                (isCardExpanded && contentScrollState.firstVisibleItemIndex == 0 &&
                                        contentScrollState.firstVisibleItemScrollOffset == 0)

                        if (canDragCard) {
                            val heightChange = dragAmount / size.height
                            cardOffset = (cardOffset + heightChange).coerceIn(0.18f, 0.8f)
                        }
                    }
                },
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Drag Handle
                Box(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .size(width = 50.dp, height = 4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.Gray.copy(alpha = 0.4f))
                        .align(Alignment.CenterHorizontally)
                )

                // Content Area
                if (isCardExpanded) {
                    // Mode Expanded: Content bisa di-scroll
                    LazyColumn(
                        state = contentScrollState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = Spacing_50)
                    ) {
                        item {
                            Text(
                                text = "Detail Artikel",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            Text(
                                text = timeStamp.toFormattedDate(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    textAlign = TextAlign.End,
                                    color = Color.Gray
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 28.sp
                                )
                            )
                        }

                        // Split content into multiple items untuk demonstrasi scroll
                        item {
                            Text(
                                text = description.toAnnotatedString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    textAlign = TextAlign.Justify,
                                    lineHeight = 20.sp
                                )
                            )
                        }

                        // Extra space
                        item { Spacer(modifier = Modifier.height(50.dp)) }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Detail Artikel",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = timeStamp.toFormattedDate(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                textAlign = TextAlign.End,
                                color = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 28.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = description.toAnnotatedString(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                textAlign = TextAlign.Justify,
                                lineHeight = 20.sp
                            ),
                            color = Color.Black.copy(alpha = 0.7f)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Visual indicator untuk drag up
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .size(4.dp)
                                        .background(
                                            Color.Gray.copy(alpha = 0.5f),
                                            shape = CircleShape
                                        )
                                )
                                if (it < 2) Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}