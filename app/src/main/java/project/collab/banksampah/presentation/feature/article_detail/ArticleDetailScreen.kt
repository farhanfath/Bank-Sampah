package project.collab.banksampah.presentation.feature.article_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.feature.article_detail.component.BackActionButton
import project.collab.banksampah.presentation.feature.article_detail.component.BackDropImage
import project.collab.banksampah.presentation.feature.article_detail.component.DetailInformation
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.utils.fromHtml

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleDetailScreen(
    onBackClick: () -> Unit,
    articleId: String,
    title: String,
    description: String,
    imageUrl: String,
    timeStamp: String,
    editor: String
) {
    /**
     * image and scroll behavior
     */
    val maxImageHeight = 400.dp
    val minImageHeight = 200.dp
    var currentImageHeight by remember { mutableStateOf(maxImageHeight) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Calculate the change in image height based on scroll delta
                val delta = available.y
                val newImageHeight = currentImageHeight + delta.dp
                val previousImageHeight = currentImageHeight

                // Constrain the image height within the allowed bounds
                currentImageHeight = newImageHeight.coerceIn(minImageHeight, maxImageHeight)
                val consumed = currentImageHeight - previousImageHeight

                // Return the consumed scroll amount
                return if (currentImageHeight > minImageHeight || delta < 0) {
                    Offset(0f, consumed.value)
                } else {
                    Offset.Zero
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(nestedScrollConnection)
        ) {
            BackDropImage(
                currentImageHeight = currentImageHeight,
                articleImageUrl = imageUrl
            )

            BackActionButton(
                modifier = Modifier.padding(innerPadding),
                onBackClick = onBackClick,
            )

            /**
             * detail Content
             */
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        IntOffset(0, currentImageHeight.roundToPx())
                    },
                contentPadding = PaddingValues(bottom = currentImageHeight)
            ) {
                item {
                    DetailInformation(
                        title = title,
                        timeStamp = timeStamp,
                        editor = editor
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spacing_20, vertical = Spacing_10)
                    ) {
                        Text(
                            style = MaterialTheme.typography.bodyMedium.copy(
                                textAlign = TextAlign.Justify
                            ),
                            text = description.fromHtml().toString()
                        )
                    }
                }
            }
        }
    }
}