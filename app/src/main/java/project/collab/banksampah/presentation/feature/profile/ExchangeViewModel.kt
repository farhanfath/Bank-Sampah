package project.collab.banksampah.presentation.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.domain.usecase.ExchangeUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class ExchangeViewModel (
    private val exchangeUseCase: ExchangeUseCase
) : ViewModel() {

    private val _pointRequestExchangeState = MutableStateFlow(PointExchangeUiState())
    val pointRequestExchangeState = _pointRequestExchangeState.asStateFlow()

    val trashExchangeHistoryListState =
        exchangeUseCase.getAllUserTrashExchangeHistory()
        .cachedIn(viewModelScope)

    fun requestPointExchange(pointRequest: RedeemPointRequest) {
        _pointRequestExchangeState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            exchangeUseCase.postRequestPointExchange(pointRequest)
                .onSuccess { response ->
                    _pointRequestExchangeState.update {
                        it.copy(
                            isLoading = false,
                            data = response,
                            isRequestExchangeSuccess = true
                        )
                    }
                }
                .onError { error ->
                    _pointRequestExchangeState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message,
                            isRequestExchangeFailed = true
                        )
                    }
                }
        }
    }
}