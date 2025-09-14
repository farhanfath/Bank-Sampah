package project.collab.banksampah.presentation.feature.typetrash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.domain.usecase.TypeOfTrashUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess
import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus

class TypeOfTrashViewModel (
    private val typeOfTrashUseCase: TypeOfTrashUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TypeOfTrashUiState())
    val uiState = _uiState.asStateFlow()

    private val _typeOfTrashDetailState = MutableStateFlow(TypeOfTrashUiState())
    val typeOfTrashDetailState = _typeOfTrashDetailState.asStateFlow()

    private val _searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val typeOfTrashes: Flow<PagingData<TypeOfTrash>>  = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            typeOfTrashUseCase.getTypeOfTrashList(trashType = query)
        }
        .cachedIn(viewModelScope)

    fun getTypeOfTrashById(id: String) {
        _typeOfTrashDetailState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            typeOfTrashUseCase.getTypeOfTrashById(id)
                .onSuccess {
                    _typeOfTrashDetailState.update {
                        it.copy(
                            isLoading = false,
                            data = it.data
                        )
                    }
                }
                .onError { error ->
                    _typeOfTrashDetailState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        _searchQuery.value = query.ifBlank { null }
    }

    fun clearSearch() {
        _uiState.update {
            it.copy(
                searchQuery = ""
            )
        }
        _searchQuery.value = null
    }
}