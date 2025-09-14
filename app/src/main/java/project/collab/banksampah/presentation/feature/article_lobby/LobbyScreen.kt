package project.collab.banksampah.presentation.feature.article_lobby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.presentation.components.CommonEmptyState
import project.collab.banksampah.presentation.feature.article_lobby.component.SearchBar
import project.collab.banksampah.presentation.feature.article_lobby.component.carouselSection
import project.collab.banksampah.presentation.feature.article_lobby.component.recommendArticleSection
import project.collab.banksampah.presentation.feature.article_lobby.component.searchArticleSection
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun LobbyScreen(
    articleViewModel: ArticleViewModel = koinViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val uiState by articleViewModel.uiState.collectAsState()
    val articles = articleViewModel.articles.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SearchBar(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = articleViewModel::updateSearchQuery,
                onClearSearch = articleViewModel::clearSearch
            )
        }

        when {
            uiState.searchQuery.isNotBlank() -> {
                searchArticleSection(
                    articles = articles,
                    articlesUiState = uiState,
                    onArticleClick = onArticleClick
                )
            }
            else -> {
                item("latest_article_header") {
                    Text(
                        text = "Artikel Terkini",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = Spacing_30, vertical = Spacing_8)
                    )
                }

                carouselSection(
                    articles = articles,
                    onArticleClick = onArticleClick
                )

                item(key = "recommendation_article_header") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Spacing_30)
                    ) {
                        Text(
                            text = "Rekomendasi",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.padding(bottom = Spacing_16)
                        )
                    }
                }

                recommendArticleSection(
                    articles = articles,
                    onArticleClick = onArticleClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLobbyScreen() {
    LobbyScreen(
        onArticleClick = {}
    )
}