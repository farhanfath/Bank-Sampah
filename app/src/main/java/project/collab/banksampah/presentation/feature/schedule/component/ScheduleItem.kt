package project.collab.banksampah.presentation.feature.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus
import project.collab.banksampah.presentation.feature.schedule.util.getScheduleStatus
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_8
import project.collab.banksampah.presentation.utils.toFormattedDate
import project.collab.banksampah.presentation.utils.toFormattedTime

// Schedule Card
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCard(
    schedule: Schedule,
    onClick: () -> Unit
) {
    val status = getScheduleStatus(schedule.openAt, schedule.closeAt)
    val statusColor = when (status) {
        ScheduleStatus.OPEN -> Color(0xFF4CAF50)
        ScheduleStatus.CLOSED -> Color(0xFFF44336)
        ScheduleStatus.UPCOMING -> Color(0xFFFF9800)
        ScheduleStatus.ALL -> Color.Gray
    }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing_10, vertical = Spacing_8),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header dengan status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = schedule.bsuBranchName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = schedule.bsuBranchCode,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF666666)
                        )
                    )
                }

                // Status Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = statusColor.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = status.displayName,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = statusColor,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Time Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Open Time
                TimeInfoCard(
                    label = "Buka",
                    time = schedule.openAt.toFormattedTime(),
                    date = schedule.openAt.toFormattedDate(),
                    icon = Icons.Default.PlayArrow,
                    iconColor = Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )

                // Close Time
                TimeInfoCard(
                    label = "Tutup",
                    time = schedule.closeAt.toFormattedTime(),
                    date = schedule.closeAt.toFormattedDate(),
                    icon = Icons.Default.Stop,
                    iconColor = Color(0xFFF44336),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Editor: ${schedule.editor}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF999999)
                    )
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Detail",
                    tint = Color(0xFFBDBDBD),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}