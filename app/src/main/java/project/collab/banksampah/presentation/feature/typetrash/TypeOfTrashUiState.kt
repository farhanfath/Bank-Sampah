package project.collab.banksampah.presentation.feature.typetrash

import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash

data class TypeOfTrashUiState(
    val isLoading: Boolean = false,
    val data: TypeOfTrash? = null,
    val error: String? = null,
    val searchQuery: String = "",
)