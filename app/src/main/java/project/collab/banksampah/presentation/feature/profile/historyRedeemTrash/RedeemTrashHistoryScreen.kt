package project.collab.banksampah.presentation.feature.profile.historyRedeemTrash

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
import project.collab.banksampah.presentation.components.base.BaseScreenWithListItem
import project.collab.banksampah.presentation.components.base.BaseTitleSection
import project.collab.banksampah.presentation.feature.profile.ExchangeViewModel
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.components.RedeemTrashCard
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.components.RedeemTrashCardShimmer
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.utils.handlePagingState

@Composable
fun RedeemTrashHistoryScreen(
    onBackClick: () -> Unit,
    exchangeViewModel: ExchangeViewModel = koinViewModel()
) {
    val redeemTrashHistoryListState = exchangeViewModel.trashExchangeHistoryListState.collectAsLazyPagingItems()

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
                    title = "Riwayat Penukaran\nSampah",
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

                Spacer(modifier = Modifier.size(Spacing_10))
            }

            handlePagingState(
                items = redeemTrashHistoryListState,
                onLoading = {
                    items(5) {
                        RedeemTrashCardShimmer()
                    }
                },
                onSuccess = {
                    when {
                        redeemTrashHistoryListState.itemCount == 0 -> {
                            item {
                                CommonEmptyState(
                                    message = "Belum ada Penukaran Sampah yang dilakukan"
                                )
                            }
                        }
                        else -> {
                            items(
                                count = redeemTrashHistoryListState.itemCount,
                                key = { index ->
                                    val redeemTrash = redeemTrashHistoryListState[index]
                                    if (redeemTrash != null) {
                                        "redeemTrash_${redeemTrash.id}_$index"
                                    } else {
                                        "null_$index"
                                    }
                                }
                            ) { index ->
                                redeemTrashHistoryListState[index]?.let { data ->
                                    RedeemTrashCard(
                                        redeemTrashHistoryData = data,
                                        onClick = {}
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
}