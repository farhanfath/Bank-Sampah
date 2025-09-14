package project.collab.banksampah.presentation.feature.typetrash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.presentation.components.CommonEmptyState
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypeCard
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypeCardShimmer
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypeDetailDialog
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypesHeader
import project.collab.banksampah.presentation.utils.handlePagingState
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeTrashScreen(
    viewModel: TypeOfTrashViewModel = koinViewModel()
) {
    val trashTypeUiState by viewModel.uiState.collectAsState()
    val trashTypes = viewModel.typeOfTrashes.collectAsLazyPagingItems()

    val trashTypeDetailDialogState = rememberVisibilityState()
    var selectedTrashType by remember { mutableStateOf<TypeOfTrash?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TrashTypesHeader(
                searchQuery = trashTypeUiState.searchQuery,
                onSearchQueryChange = viewModel::updateSearchQuery,
                onRefresh = trashTypes::refresh,
                onClearSearch = viewModel::clearSearch
            )
        }

        handlePagingState(
            items = trashTypes,
            onLoading = {
                items(5) {
                    TrashTypeCardShimmer()
                }
            },
            onSuccess = {
                when {
                    trashTypes.itemCount == 0 -> {
                        item {
                            CommonEmptyState(
                                message = if (trashTypeUiState.searchQuery.isNotBlank()) {
                                    "Tidak ada jenis sampah \"${trashTypeUiState.searchQuery}\""
                                } else {
                                    "Belum ada jenis sampah tersedia"
                                }
                            )
                        }
                    }
                    else -> {
                        items(
                            count = trashTypes.itemCount,
                            key = {index ->
                                val typeTrashes = trashTypes[index]
                                if (typeTrashes != null) {
                                    "typeTrashes_${typeTrashes.id}_$index"
                                } else {
                                    "null_$index"
                                }
                            }
                        ) { index ->
                            trashTypes[index]?.let { typeOfTrash ->
                                TrashTypeCard(
                                    trashType = typeOfTrash,
                                    onClick = {
                                        selectedTrashType = typeOfTrash
                                        trashTypeDetailDialogState.show()
                                    }
                                )
                            }
                        }
                    }
                }
            },
            onError = {}
        )
    }

    selectedTrashType?.let { typeOfTrash ->
        TrashTypeDetailDialog(
            trashType = typeOfTrash,
            isVisible = trashTypeDetailDialogState.value,
            onDismiss = {
                selectedTrashType = null
                trashTypeDetailDialogState.hide()
            }
        )
    }
}