package project.collab.banksampah.presentation.feature.auth.register.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import project.collab.banksampah.domain.model.response.bsu.Bsu
import project.collab.banksampah.presentation.components.base.BaseTextField
import project.collab.banksampah.presentation.feature.auth.register.BsuDropdownState
import project.collab.banksampah.presentation.feature.auth.register.components.bsu.BsuDropdownItem
import project.collab.banksampah.presentation.feature.auth.register.components.bsu.BsuDropdownItemShimmer
import project.collab.banksampah.presentation.feature.auth.register.components.bsu.EmptyStateItem
import project.collab.banksampah.presentation.feature.auth.register.components.bsu.ErrorItem
import project.collab.banksampah.presentation.theme.AccentGrey
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.utils.HandleComposePagingAppendState
import project.collab.banksampah.presentation.utils.HandleComposePagingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableBsuDropDown(
    state: BsuDropdownState,
    bsuPagingItems: LazyPagingItems<Bsu>,
    onSearchQueryChange: (String) -> Unit,
    onBsuSelect: (Bsu) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = state.isExpanded,
        onExpandedChange = { expanded ->
            if (!expanded) {
                onExpandedChange(false)
                focusManager.clearFocus()
            } else if (!state.isExpanded) {
                onExpandedChange(true)
            }
        },
    ) {
        BaseTextField(
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused && !state.isExpanded) {
                        onExpandedChange(true)
                    }
                },
            hint = "Pilih Cabang BSU",
            value = state.selectedBsu?.bsuBranchName ?: state.searchQuery,
            onValueChange = { query ->
                onSearchQueryChange(query)
                if (!state.isExpanded) onExpandedChange(true)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Words
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Branch",
                    tint = AccentGrey,
                    modifier = Modifier.size(Size_20)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = state.isExpanded
                )
            }
        )

        ExposedDropdownMenu(
            expanded = state.isExpanded,
            onDismissRequest = {
                onExpandedChange(false)
                focusManager.clearFocus()
            },
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            HandleComposePagingState(
                items = bsuPagingItems,
                onLoading = {
                    repeat(3) {
                        BsuDropdownItemShimmer()
                    }
                },
                onSuccess = {
                    when {
                        bsuPagingItems.itemCount == 0 -> {
                            EmptyStateItem()
                        }
                        else -> {
                            val items = bsuPagingItems.itemSnapshotList.items
                            items.forEach { bsu ->
                                BsuDropdownItem(
                                    bsu = bsu,
                                    isSelected = state.selectedBsu?.id == bsu.id,
                                    onClick = {
                                        onBsuSelect(bsu)
                                        onExpandedChange(false)
                                        focusManager.clearFocus()
                                    }
                                )
                            }
                        }
                    }

                    HandleComposePagingAppendState(
                        items = bsuPagingItems,
                        onLoading = {
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(20.dp),
                                            strokeWidth = 2.dp,
                                            color = PrimaryGreen
                                        )
                                    }
                                },
                                onClick = { }
                            )
                        },
                        onNotLoading = {},
                        onError = {
                            ErrorItem(
                                message = "Gagal memuat lebih banyak data",
                                onRetryClick = { bsuPagingItems.retry() }
                            )
                        }
                    )
                },
                onError = { error ->
                    ErrorItem(
                        message = "Gagal memuat data",
                        onRetryClick = { bsuPagingItems.retry() }
                    )
                }
            )
        }
    }

}


