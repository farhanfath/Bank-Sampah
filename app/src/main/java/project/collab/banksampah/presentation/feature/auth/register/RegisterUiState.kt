package project.collab.banksampah.presentation.feature.auth.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null,
    val isRegisterSuccess: Boolean = false,
    val isRegisterFailed: Boolean = false
)