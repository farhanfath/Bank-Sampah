package project.collab.banksampah.presentation.feature.article_lobby.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.LazyPagingItems
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Font_10
import project.collab.banksampah.presentation.theme.Font_12
import project.collab.banksampah.presentation.theme.Font_14
import project.collab.banksampah.presentation.theme.Font_18
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.PrimaryGrey
import project.collab.banksampah.presentation.theme.Size_10
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Size_12
import project.collab.banksampah.presentation.theme.Size_15
import project.collab.banksampah.presentation.theme.Size_150
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_80
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8
import project.collab.banksampah.presentation.utils.handlePagingAppendState
import project.collab.banksampah.presentation.utils.handlePagingState
import project.collab.banksampah.presentation.utils.toAnnotatedString
import project.collab.banksampah.presentation.utils.toFormattedDateTime

fun LazyListScope.recommendArticleSection(
    articles: LazyPagingItems<Article>,
    onArticleClick: (Article) -> Unit
) {
    handlePagingState(
        items = articles,
        onLoading = {
            items(5) {
                ShimmerItemRecommendationArticle()
            }
        },
        onSuccess = {
            items(
                count = articles.itemCount,
                key = { index ->
                    val article = articles[index]
                    if (article != null) {
                        "article_${article.id}_$index"
                    } else {
                        "null_$index"
                    }
                }
            ) { index ->
                articles[index]?.let { article ->
                    RecommendationArticleItem(
                        article = article,
                        onClick = { onArticleClick(article) }
                    )
                }
            }

            handlePagingAppendState(
                items = articles,
                onLoading = {
                    item {
                        BaseShimmer(
                            modifier = Modifier
                                .padding(horizontal = Spacing_10)
                                .height(Size_100)
                                .fillMaxWidth()
                        )
                    }
                },
                onError = {
                    item { Text("Terjadi error saat load awal") }
                },
                onNotLoading = {}
            )
        },
        onError = {}
    )
}

@Composable
fun RecommendationArticleItem(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(horizontal = Spacing_20)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = Spacing_8)
        ) {
            // Article Image
            Box(
                modifier = Modifier
                    .width(Size_150)
                    .height(Size_100)
                    .clip(RoundedCornerShape(Spacing_8))
            ) {
                BaseImage(
                    image = article.coverImageUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(Size_12))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(Size_80),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Title
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = Font_18,
                            fontSize = Font_14
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )

                    Spacer(modifier = Modifier.height(Spacing_4))

                    // Description
                    Text(
                        text = article.description.toAnnotatedString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = Font_14,
                        fontSize = Font_12
                    )
                }

                // Date
                Text(
                    text = article.timestamp.toFormattedDateTime(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = Font_10
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = PrimaryGreen
        )
    }
}

@Composable
fun ShimmerItemRecommendationArticle() {
    Column(
        modifier = Modifier.padding(horizontal = Spacing_20)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing_8)
        ) {
            // Article Image
            BaseShimmer(
                modifier = Modifier
                    .width(Size_150)
                    .height(Size_100)
            )

            Spacer(modifier = Modifier.width(Size_12))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(Size_80),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    BaseShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Size_20)
                    )
                    Spacer(modifier = Modifier.height(Spacing_4))
                    BaseShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Size_15)
                    )
                }

                BaseShimmer(
                    modifier = Modifier
                        .width(Size_100)
                        .height(Size_10)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = PrimaryGrey
        )
    }
}