package project.collab.banksampah.presentation.feature.schedule.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.utils.toFormattedDateTime

// Additional Info Section
@Composable
fun AdditionalInfoSection(
    editor: String,
    addedAt: String,
    scheduleId: String
) {
    Column {
        Text(
            text = "Informasi Tambahan",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFFAFAFA)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoRow(
                    icon = Icons.Default.Person,
                    label = "Editor",
                    value = editor,
                    iconColor = Color(0xFF2196F3)
                )

                InfoRow(
                    icon = Icons.Default.Schedule,
                    label = "Dibuat",
                    value = addedAt.toFormattedDateTime(),
                    iconColor = Color(0xFF9C27B0)
                )

                InfoRow(
                    icon = Icons.Default.Tag,
                    label = "ID Jadwal",
                    value = scheduleId,
                    iconColor = Color(0xFF607D8B)
                )
            }
        }
    }
}