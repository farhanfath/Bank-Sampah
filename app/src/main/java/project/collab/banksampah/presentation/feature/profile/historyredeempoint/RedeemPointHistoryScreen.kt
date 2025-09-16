package project.collab.banksampah.presentation.feature.profile.historyredeempoint

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.presentation.components.CommonEmptyState
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.feature.profile.ExchangeViewModel
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointCard
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointCardShimmer
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointFailedSection
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.utils.handlePagingState

@Composable
fun RedeemPointHistoryScreen(
    onBackClick: () -> Unit,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    val redeemPointHistoryState = exchangeViewModel.redeemPointHistoryListState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = it.calculateTopPadding(), horizontal = Spacing_16)
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
                        redeemPointHistoryState.itemCount == 0 -> {
                            item {
                                CommonEmptyState(
                                    message = "Belum ada penukaran poin yang dilakukan"
                                )
                            }
                        }
                        else -> {
                            items(
                                count = redeemPointHistoryState.itemCount,
                                key = { index ->
                                    val redeemPoint = redeemPointHistoryState[index]
                                    if (redeemPoint != null) {
                                        "redeemPoint_${redeemPoint.id}_$index"
                                    } else {
                                        "null_$index"
                                    }
                                }
                            ) { index ->
                                redeemPointHistoryState[index]?.let { data ->
                                    RedeemPointCard(
                                        historyRedeemPointData = data,
                                        onClick = {}
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
}