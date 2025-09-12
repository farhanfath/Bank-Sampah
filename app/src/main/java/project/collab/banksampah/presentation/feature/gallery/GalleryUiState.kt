package project.collab.banksampah.presentation.feature.gallery

import project.collab.banksampah.domain.model.response.gallery.Gallery

data class GalleryUiState(
    val isLoading: Boolean = false,
    val data: Gallery? = null,
    val error: String? = null,
)