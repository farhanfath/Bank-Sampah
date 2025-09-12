package project.collab.banksampah.presentation.feature.profile.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.usecase.AuthUseCase
import project.collab.banksampah.domain.usecase.UserUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class UserViewModel(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _userDataState = MutableStateFlow(UserUiState())
    val userDataState = _userDataState.asStateFlow()

    val isLoggedIn: Flow<Boolean> = authUseCase.isLoggedIn()

    fun getUserData() {
        _userDataState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            userUseCase.getUserById()
                .onSuccess { response ->
                    _userDataState.update {
                        it.copy(
                            isLoading = false,
                            userData = response,
                            isDataLoaded = true
                        )
                    }
                }
                .onError { error ->
                    _userDataState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message.toString()
                        )
                    }
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authUseCase.logout()
        }
    }
}