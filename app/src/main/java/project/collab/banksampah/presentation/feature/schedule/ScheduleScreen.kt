package project.collab.banksampah.presentation.feature.schedule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
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
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.schedule.component.EmptyScheduleState
import project.collab.banksampah.presentation.feature.schedule.component.ErrorScheduleState
import project.collab.banksampah.presentation.feature.schedule.component.FilterSection
import project.collab.banksampah.presentation.feature.schedule.component.ScheduleCard
import project.collab.banksampah.presentation.feature.schedule.component.ScheduleCardShimmer
import project.collab.banksampah.presentation.feature.schedule.component.ScheduleHeader
import project.collab.banksampah.presentation.feature.schedule.detail.ScheduleDetailDialog
import project.collab.banksampah.presentation.feature.schedule.util.getScheduleStatus
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.utils.handlePagingState
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = koinViewModel()
) {
    val uiState by scheduleViewModel.uiState.collectAsState()
    val schedules = scheduleViewModel.schedules.collectAsLazyPagingItems()

    var selectedSchedule by remember { mutableStateOf<Schedule?>(null) }

    val scheduleDialogState = rememberVisibilityState()

    val filteredSchedules = remember(schedules.itemSnapshotList, uiState.selectedStatus) {
        if (uiState.selectedStatus == ScheduleStatus.ALL) {
            schedules.itemSnapshotList.items
        } else {
            schedules.itemSnapshotList.items.filter { schedule ->
                schedule?.let { getScheduleStatus(it.openAt, it.closeAt) == uiState.selectedStatus } == true
            }
        }
    }

    LazyColumn {
        item {
            ScheduleHeader(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = scheduleViewModel::updateSearchQuery,
                onRefresh = { schedules.refresh() },
                onClearSearch = scheduleViewModel::clearSearch
            )
        }

        item {
            FilterSection(
                selectedStatus = uiState.selectedStatus,
                onStatusFilterChange = scheduleViewModel::updateStatusFilter,
                totalItems = schedules.itemCount,
                filteredItems = filteredSchedules.size
            )
        }

        handlePagingState(
            items = schedules,
            onLoading = {
                items(5) {
                    ScheduleCardShimmer()
                }
            },
            onSuccess = {
                when {
                    schedules.itemCount == 0 -> {
                        item {
                            EmptyScheduleState(
                                message = if (uiState.searchQuery.isNotBlank()) {
                                    "Tidak ada jadwal untuk \"${uiState.searchQuery}\""
                                } else {
                                    "Belum ada jadwal tersedia"
                                }
                            )
                        }
                    }
                    filteredSchedules.isEmpty() && uiState.selectedStatus != ScheduleStatus.ALL -> {
                        item {
                            EmptyScheduleState(
                                message = "Tidak ada jadwal dengan status \"${uiState.selectedStatus.displayName}\""
                            )
                        }
                    }
                    else -> {
                        items(
                            count = filteredSchedules.size,
                            key = { index ->
                                val schedule = filteredSchedules[index]
                                "filtered_schedule_${schedule.id}_$index"
                            }
                        ) { index ->
                            schedules[index]?.let { schedule ->
                                ScheduleCard(
                                    schedule = schedule,
                                    onClick = {
                                        selectedSchedule = schedule
                                        scheduleDialogState.show()
                                    }
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.size(Spacing_20))
                }
            },
            onError = {
                item {
                    ErrorScheduleState(
                        message = "Gagal memuat jadwal",
                        onRetry = { schedules.retry() }
                    )
                }
            }
        )
    }

    selectedSchedule?.let { schedule ->
        ScheduleDetailDialog(
            schedule = schedule,
            isVisible = scheduleDialogState.value,
            onDismiss = {
                selectedSchedule = null
                scheduleDialogState.hide()
            }
        )
    }
}




