package project.collab.banksampah.presentation.feature.article_lobby.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.feature.article_lobby.component.recommend.RecommendationArticleItem
import project.collab.banksampah.presentation.feature.article_lobby.component.recommend.ShimmerItemRecommendationArticle
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.utils.handlePagingAppendState
import project.collab.banksampah.presentation.utils.handlePagingState

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
