package project.collab.banksampah.presentation.feature.profile.user

import project.collab.banksampah.domain.model.User

data class UserUiState(
    val isLoading: Boolean = false,
    val userData: User? = null,
    val error: String? = null,
    val isDataLoaded: Boolean = false
)