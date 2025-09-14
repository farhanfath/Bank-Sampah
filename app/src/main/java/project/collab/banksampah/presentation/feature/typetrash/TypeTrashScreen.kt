package project.collab.banksampah.presentation.feature.typetrash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.presentation.components.CommonEmptyState
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypeCard
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypeCardShimmer
import project.collab.banksampah.presentation.feature.typetrash.component.TrashTypesHeader
import project.collab.banksampah.presentation.utils.handlePagingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeTrashScreen(
    viewModel: TypeOfTrashViewModel = koinViewModel(),
    onTrashTypeClick: (TypeOfTrash) -> Unit = {}
) {
    val trashTypeUiState by viewModel.uiState.collectAsState()
    val trashTypes = viewModel.typeOfTrashes.collectAsLazyPagingItems()

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
                                    onClick = { onTrashTypeClick(typeOfTrash) }
                                )
                            }
                        }
                    }
                }
            },
            onError = {}
        )
    }
}