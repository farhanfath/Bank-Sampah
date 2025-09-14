package project.collab.banksampah.presentation.feature.auth.register

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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.usecase.BsuUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class RegisterViewModel (
    private val authUseCase: AuthUseCase,
    private val bsuUseCase: BsuUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState = _registerState.asStateFlow()

    private val _dropdownState = MutableStateFlow(BsuDropdownState())
    val dropdownState = _dropdownState.asStateFlow()

    private val _searchQuery = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val bsuPagingData: Flow<PagingData<Bsu>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            bsuUseCase.getBsuList(query)
        }
        .cachedIn(viewModelScope)

    fun register(registerRequest: RegisterRequest) {
        _registerState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.userRegister(registerRequest)
                .onSuccess { response ->
                    _registerState.update {
                        it.copy(
                            isLoading = false,
                            message = response.message,
                            isRegisterSuccess = true
                        )
                    }
                }
                .onError { error ->
                    _registerState.update {
                        it.copy(
                            isLoading = false,
                            message = error.message,
                            isRegisterFailed = true
                        )
                    }
                }
        }
    }

    fun updateSearchQuery(query: String) {
        _dropdownState.update { it.copy(searchQuery = query) }
        _searchQuery.value = query.ifBlank { null }
    }

    fun selectBsu(bsu: Bsu) {
        _dropdownState.update {
            it.copy(
                selectedBsu = bsu,
                searchQuery = ""
            )
        }
        _searchQuery.value = null
    }

    fun updateDropdownExpanded(expanded: Boolean) {
        _dropdownState.update { it.copy(isExpanded = expanded) }

        // Clear selection ketika buka dropdown untuk search baru
        if (expanded && _dropdownState.value.selectedBsu != null) {
            _dropdownState.update {
                it.copy(
                    selectedBsu = null,
                    searchQuery = ""
                )
            }
            _searchQuery.value = null
        }
    }

    fun clearBsuSelection() {
        _dropdownState.update {
            it.copy(
                selectedBsu = null,
                searchQuery = ""
            )
        }
        _searchQuery.value = null
    }
}