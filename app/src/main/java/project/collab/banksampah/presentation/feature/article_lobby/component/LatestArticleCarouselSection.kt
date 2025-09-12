package project.collab.banksampah.presentation.feature.article_lobby.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.presentation.feature.article_lobby.component.carousel.ArticleCarousel
import project.collab.banksampah.presentation.feature.article_lobby.component.carousel.CarouselEmptyState
import project.collab.banksampah.presentation.feature.article_lobby.component.carousel.CarouselErrorState
import project.collab.banksampah.presentation.feature.article_lobby.component.carousel.CarouselLoadingState
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.utils.handlePagingState

fun LazyListScope.carouselSection(
    articles: LazyPagingItems<Article>,
    onArticleClick: (Article) -> Unit
) {
    handlePagingState(
        items = articles,
        onLoading = {
            item(key = "carousel_loading") {
                CarouselLoadingState(
                    modifier = Modifier.padding(horizontal = Spacing_30)
                )
            }
        },
        onSuccess = {
            if (articles.itemCount == 0) {
                item(key = "carousel_empty") {
                    CarouselEmptyState(
                        modifier = Modifier.padding(horizontal = Spacing_30)
                    )
                }
            } else {
                item(key = "carousel_content") {
                    val articlesForCarousel = remember(articles.itemCount) {
                        (0 until minOf(articles.itemCount, 5)).mapNotNull { index ->
                            articles[index]
                        }
                    }
                    if (articlesForCarousel.isNotEmpty()) {
                        ArticleCarousel(
                            articles = articlesForCarousel,
                            onArticleClick = onArticleClick
                        )
                        Spacer(modifier = Modifier.size(Spacing_20))
                    }
                }
            }
        },
        onError = {
            item(key = "carousel_error") {
                CarouselErrorState(
                    onRetry = { articles.retry() },
                    modifier = Modifier.padding(horizontal = Spacing_30)
                )
            }
        }
    )
}

