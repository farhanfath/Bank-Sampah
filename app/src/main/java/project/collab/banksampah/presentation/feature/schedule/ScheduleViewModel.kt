package project.collab.banksampah.presentation.feature.schedule

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
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.domain.usecase.ScheduleUseCase

class ScheduleViewModel (
    private val scheduleUseCase: ScheduleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val schedules: Flow<PagingData<Schedule>> = _searchQuery
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            scheduleUseCase.getScheduleList(bsuBranchName = query)
        }
        .cachedIn(viewModelScope)


    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        _searchQuery.value = query.ifBlank { null }
    }

    fun updateStatusFilter(status: ScheduleStatus) {
        _uiState.update { it.copy(selectedStatus = status) }
    }

    // Clear search
    fun clearSearch() {
        _uiState.update {
            it.copy(
                searchQuery = "",
                selectedStatus = ScheduleStatus.ALL
            )
        }
        _searchQuery.value = null
    }
}