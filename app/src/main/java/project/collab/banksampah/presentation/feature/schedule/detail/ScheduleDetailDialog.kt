package project.collab.banksampah.presentation.feature.schedule.detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import project.collab.banksampah.domain.model.response.schedule.Schedule
import project.collab.banksampah.presentation.components.base.BaseDialog
import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus
import project.collab.banksampah.presentation.feature.schedule.detail.component.AdditionalInfoSection
import project.collab.banksampah.presentation.feature.schedule.detail.component.BranchInfoCard
import project.collab.banksampah.presentation.feature.schedule.detail.component.TimeDetailsSection
import project.collab.banksampah.presentation.feature.schedule.util.getScheduleStatus

@Composable
fun ScheduleDetailDialog(
    schedule: Schedule,
    isVisible: Boolean,
    onDismiss: () -> Unit,
) {
    val status = getScheduleStatus(schedule.openAt, schedule.closeAt)
    val statusColor = when (status) {
        ScheduleStatus.OPEN -> Color(0xFF4CAF50)
        ScheduleStatus.CLOSED -> Color(0xFFF44336)
        ScheduleStatus.UPCOMING -> Color(0xFFFF9800)
        ScheduleStatus.ALL -> Color.Gray
    }

    BaseDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Header dengan Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Detail Jadwal",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    )

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color(0xFF666666)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Status Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(25.dp),
                        color = statusColor.copy(alpha = 0.15f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(statusColor, CircleShape)
                            )

                            Text(
                                text = status.displayName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = statusColor,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            if (status == ScheduleStatus.OPEN) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = statusColor,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Branch Info Card
                BranchInfoCard(
                    branchName = schedule.bsuBranchName,
                    branchCode = schedule.bsuBranchCode
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Time Details Section
                TimeDetailsSection(
                    openAt = schedule.openAt,
                    closeAt = schedule.closeAt,
                    status = status
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Additional Info
                AdditionalInfoSection(
                    editor = schedule.editor,
                    addedAt = schedule.addedAt,
                    scheduleId = schedule.id
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}