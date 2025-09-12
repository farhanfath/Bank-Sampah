package project.collab.banksampah.presentation.feature.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import project.collab.banksampah.domain.usecase.GalleryUseCase
import project.collab.banksampah.domain.utils.onError
import project.collab.banksampah.domain.utils.onSuccess

class GalleryViewModel (
    private val galleryUseCase: GalleryUseCase
) : ViewModel() {

    private val _galleryDetailState = MutableStateFlow(GalleryUiState())
    val galleryDetailState = _galleryDetailState.asStateFlow()

    val galleries = galleryUseCase.getGalleryList()
        .cachedIn(viewModelScope)

    fun getGalleryById(id: String) {
        _galleryDetailState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            galleryUseCase.getGalleryById(id)
                .onSuccess {
                    _galleryDetailState.update {
                        it.copy(
                            isLoading = false,
                            data = it.data
                        )
                    }
                }
                .onError {
                    _galleryDetailState.update {
                        it.copy(
                            isLoading = false,
                            error = it.error
                        )
                    }
                }
        }
    }

}