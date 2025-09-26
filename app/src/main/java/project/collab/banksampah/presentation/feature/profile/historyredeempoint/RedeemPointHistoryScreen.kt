package project.collab.banksampah.presentation.feature.profile.historyredeempoint

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistory
import project.collab.banksampah.presentation.components.CommonEmptyState
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.profile.ExchangeViewModel
import project.collab.banksampah.presentation.feature.profile.PointExchangeStatus
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointCard
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointCardShimmer
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointFailedSection
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointFilterChip
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.detail.RedeemPointDetailDialog
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.util.getPointStatus
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.util.toPointExchangeStatus
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.utils.handlePagingState
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.show

@Composable
fun RedeemPointHistoryScreen(
    onBackClick: () -> Unit,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    val redeemPointHistoryState = exchangeViewModel.redeemPointHistoryListState.collectAsLazyPagingItems()
    val uiState by exchangeViewModel.pointRequestExchangeState.collectAsState()

    var selectedRedeemPoint by remember { mutableStateOf<RedeemPointHistory?>(null) }
    val detailRedeemDialog = rememberVisibilityState()

    val filteredItems by remember {
        derivedStateOf {
            redeemPointHistoryState.itemSnapshotList.items.let { items ->
                if (uiState.selectedStatus == PointExchangeStatus.ALL) {
                    items
                } else {
                    items.filter {
                        it?.statusPointExchange?.toPointExchangeStatus() == uiState.selectedStatus
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), start = Spacing_16, end = Spacing_16)
        ) {
            item {
                BaseHeader(
                    title = "Riwayat Penukaran\nPoin",
                    textStyle = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    onBackClick = onBackClick
                )

                Spacer(modifier = Modifier.size(Spacing_10))

                BaseTitleSection(
                    title = "Riwayat"
                )

                RedeemPointFilterChip(
                    selectedStatus = uiState.selectedStatus,
                    onStatusSelected = { status ->
                        exchangeViewModel.updateStatusFilter(status)
                    }
                )
            }

            handlePagingState(
                items = redeemPointHistoryState,
                onLoading = {
                    items(5) {
                        RedeemPointCardShimmer()
                    }
                },
                onSuccess = {
                    when {
                        filteredItems.isEmpty() -> {
                            item {
                                CommonEmptyState(
                                    message = "Belum ada penukaran poin yang dilakukan"
                                )
                            }
                        }
                        else -> {
                            items(
                                count = filteredItems.size,
                                key = { index ->
                                    filteredItems[index]?.let { "redeemPoint_${it.id}_$index" } ?: "null_$index"
                                }
                            ) { index ->
                                filteredItems[index]?.let { data ->
                                    RedeemPointCard(
                                        historyRedeemPointData = data,
                                        onClick = {
                                            selectedRedeemPoint = data
                                            detailRedeemDialog.show()
                                        }
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.size(Spacing_20))
                            }
                        }
                    }
                },
                onError = {
                    item {
                        RedeemPointFailedSection(
                            onRetry = redeemPointHistoryState::retry
                        )
                    }
                }
            )
        }
    }

    RedeemPointDetailDialog(
        isVisible = detailRedeemDialog.value,
        redeemPointData = selectedRedeemPoint,
        onDismiss = {
            detailRedeemDialog.hide()
            selectedRedeemPoint = null
        }
    )
}