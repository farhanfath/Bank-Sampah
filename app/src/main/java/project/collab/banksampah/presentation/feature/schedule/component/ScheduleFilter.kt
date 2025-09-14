package project.collab.banksampah.presentation.feature.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus
import project.collab.banksampah.presentation.theme.PrimaryGreen

// Filter Section
@Composable
fun FilterSection(
    selectedStatus: ScheduleStatus,
    onStatusFilterChange: (ScheduleStatus) -> Unit,
    totalItems: Int,
    filteredItems: Int
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Filter Status",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1A1A1A)
                )
            )

            // Show filtered count
            if (selectedStatus != ScheduleStatus.ALL && totalItems > 0) {
                Text(
                    text = "$filteredItems dari $totalItems",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF666666)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Status Filter (client-side filtering)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ScheduleStatus.entries.toTypedArray()) { status ->
                FilterChip(
                    onClick = { onStatusFilterChange(status) },
                    label = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            // Status indicator dot
                            if (status != ScheduleStatus.ALL) {
                                val dotColor = when (status) {
                                    ScheduleStatus.OPEN -> Color(0xFF4CAF50)
                                    ScheduleStatus.CLOSED -> Color(0xFFF44336)
                                    ScheduleStatus.UPCOMING -> Color(0xFFFF9800)
                                    ScheduleStatus.ALL -> Color.Transparent
                                }
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(dotColor, CircleShape)
                                )
                            }

                            Text(
                                text = status.displayName,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    },
                    selected = selectedStatus == status,
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryGreen,
                        selectedLabelColor = Color.White,
                        containerColor = Color(0xFFF5F5F5),
                        labelColor = Color(0xFF666666)
                    )
                )
            }
        }
    }
}