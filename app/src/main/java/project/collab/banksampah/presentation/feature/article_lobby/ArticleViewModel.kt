package project.collab.banksampah.presentation.feature.article_lobby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.response.article.Article
import project.collab.banksampah.domain.usecase.ArticleUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class ArticleViewModel(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {
    private val _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery: StateFlow<String?> = _searchQuery.asStateFlow()

    // article detail state (article by id)
    private val _articleDetailState = MutableStateFlow(ArticleUiState())
    val articleDetailState = _articleDetailState.asStateFlow()

    // for paging article data
    val articles : Flow<PagingData<Article>> = articleUseCase.getArticlesList()
        .cachedIn(viewModelScope)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchedArticles: Flow<PagingData<Article>> = searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            articleUseCase.getArticlesList(query)
        }
        .cachedIn(viewModelScope)

    fun searchArticles(query: String?) {
        _searchQuery.value = query
    }

    fun getArticleById(id: String) {
        _articleDetailState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            articleUseCase.getArticleById(id)
                .onSuccess {
                    _articleDetailState.update {
                        it.copy(
                            isLoading = false,
                            data = it.data
                        )
                    }
                }
                .onError {
                    _articleDetailState.update {
                        it.copy(
                            isLoading = false,
                            error = it.error
                        )
                    }
                }
        }
    }
}