package project.collab.banksampah.presentation.feature.schedule.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.PrimaryGreen

// Empty State
@Composable
fun EmptyScheduleState(
    message: String = "Belum ada jadwal tersedia"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = "No schedules",
            tint = Color(0xFFBDBDBD),
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color(0xFF666666),
                textAlign = TextAlign.Center
            )
        )

        Text(
            text = "Coba cari dengan kata kunci lain atau refresh halaman",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF999999),
                textAlign = TextAlign.Center
            )
        )
    }
}