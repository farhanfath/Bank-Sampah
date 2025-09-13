package project.collab.banksampah.presentation.feature.typetrash.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import project.collab.banksampah.domain.model.response.type_trash.TypeOfTrash
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashTypeCard(
    trashType: TypeOfTrash,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(Spacing_10)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Image
            Card(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F9FA)
                )
            ) {
                BaseImage(
                    image = trashType.fileURL,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Trash Type & Category
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = trashType.trashType,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        ),
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryGreen.copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = trashType.unit,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = PrimaryGreen,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // Description
                Text(
                    text = trashType.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF666666)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Price & Info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rp ${String.format("%,d", trashType.price)}/${trashType.unit}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen
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
}