package project.collab.banksampah.presentation.feature.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class RegisterViewModel (
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState = _registerState.asStateFlow()

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
}