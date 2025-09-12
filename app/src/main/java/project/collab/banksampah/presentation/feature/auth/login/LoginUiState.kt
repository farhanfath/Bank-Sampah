package project.collab.banksampah.presentation.feature.auth.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null,
    val isLoginSuccess: Boolean = false,
    val isLoginFailed: Boolean = false
)