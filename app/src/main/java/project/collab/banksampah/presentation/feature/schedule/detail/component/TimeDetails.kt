package project.collab.banksampah.presentation.feature.schedule.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus
import project.collab.banksampah.presentation.feature.schedule.util.getTimeRemaining
import project.collab.banksampah.presentation.utils.toFormattedFullDateTime
import project.collab.banksampah.presentation.utils.toFormattedTime

// Time Details Section
@Composable
fun TimeDetailsSection(
    openAt: String,
    closeAt: String,
    status: ScheduleStatus
) {
    Column {
        Text(
            text = "Waktu Operasional",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Open Time Card
        TimeCard(
            label = "Waktu Buka",
            time = openAt.toFormattedTime(),
            fullDateTime = openAt.toFormattedFullDateTime(),
            icon = Icons.Default.PlayArrow,
            iconColor = Color(0xFF4CAF50),
            timeRemaining = if (status == ScheduleStatus.UPCOMING) {
                getTimeRemaining(openAt)
            } else null
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Close Time Card
        TimeCard(
            label = "Waktu Tutup",
            time = closeAt.toFormattedTime(),
            fullDateTime = closeAt.toFormattedFullDateTime(),
            icon = Icons.Default.Stop,
            iconColor = Color(0xFFF44336),
            timeRemaining = if (status == ScheduleStatus.OPEN) {
                getTimeRemaining(closeAt)
            } else null
        )
    }
}