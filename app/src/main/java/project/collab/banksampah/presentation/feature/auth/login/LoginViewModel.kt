package project.collab.banksampah.presentation.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class LoginViewModel (
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState = _loginState.asStateFlow()

    fun login(loginRequest: LoginRequest) {
        _loginState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.userLogin(loginRequest)
                .onSuccess { response ->
                    _loginState.update {
                        it.copy(
                            isLoading = false,
                            message = response.message,
                            isLoginSuccess = true,
                            isLoginFailed = false
                        )
                    }
                }
                .onError { error ->
                    _loginState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message,
                            isLoginFailed = true,
                            isLoginSuccess = false,
                        )
                    }
                }
        }
    }

    fun clearLoginResult() {
        _loginState.update {
            it.copy(
                isLoginSuccess = false,
                isLoginFailed = false,
                message = null,
                error = null
            )
        }
    }
}