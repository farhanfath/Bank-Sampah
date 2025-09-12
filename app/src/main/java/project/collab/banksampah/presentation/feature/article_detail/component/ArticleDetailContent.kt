package project.collab.banksampah.presentation.feature.article_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Compose Animation
import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Navigation
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// Lifecycle & ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.collectAsState

// Coroutines & Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Koin Dependency Injection
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.content.Context
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.presentation.components.base.BaseImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailContent(
    article: Article,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val imageHeight = 300.dp
    val imageHeightPx = with(LocalDensity.current) { imageHeight.toPx() }

    // Calculate parallax offset
    val parallaxOffset = scrollState.value * 0.5f

    // Calculate card visibility and position
    val cardOffset = maxOf(0f, scrollState.value - imageHeightPx * 0.6f)
    val cardAlpha = minOf(1f, cardOffset / (imageHeightPx * 0.3f))

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image with Parallax Effect
        BaseImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight + 100.dp) // Extra height for parallax
                .offset(y = (-parallaxOffset).dp),
            image = article.coverImageUrl,
            contentScale = ContentScale.Crop
        )

        // Dark overlay on image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = imageHeightPx
                    )
                )
        )

        // Title overlay on image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .alpha(1f - (scrollState.value / imageHeightPx).coerceIn(0f, 1f))
            )
        }

        // Scrollable Content Card
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Spacer for image
            Spacer(modifier = Modifier.height(imageHeight * 0.8f))

            // Content Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(cardAlpha),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Article Meta Info
                    ArticleMetaInfo(
                        date = article.timestamp,
                        author = article.editor,
                        readTime = article.timestamp,
                        category = article.title
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Article Title (in card)
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Article Content
                    Text(
                        text = article.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tags
                    if (article.imageFileNames.isNotEmpty()) {
                        ArticleTags(tags = article.imageFileNames)
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Related Articles or Additional Content
                    Spacer(modifier = Modifier.height(100.dp)) // Extra space at bottom
                }
            }
        }

        // Top App Bar with Back Button
        TopAppBar(
            title = {
                Text(
                    text = "Detail - Artikel",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier
                        .alpha(
                            if (scrollState.value > imageHeightPx * 0.5f) 1f else 0f
                        )
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black.copy(
                    alpha = minOf(0.8f, scrollState.value / imageHeightPx)
                )
            )
        )
    }
}

@Composable
fun ArticleMetaInfo(
    date: String,
    author: String?,
    readTime: String?,
    category: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            if (author != null) {
                Text(
                    text = "Oleh $author",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (readTime != null) {
                Text(
                    text = readTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            AssistChip(
                onClick = { },
                label = {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

@Composable
fun ArticleTags(
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Tags",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tags) { tag ->
                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            text = "#$tag",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
        }
    }
}
