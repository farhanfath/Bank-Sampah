package project.collab.banksampah.presentation.feature.typetrash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.usecase.TypeOfTrashUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class TypeOfTrashViewModel (
    private val typeOfTrashUseCase: TypeOfTrashUseCase
) : ViewModel() {

    private val _typeOfTrashDetailState = MutableStateFlow(TypeOfTrashUiState())
    val typeOfTrashDetailState = _typeOfTrashDetailState.asStateFlow()

    val typeOfTrashes = typeOfTrashUseCase.getTypeOfTrashList()
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
}