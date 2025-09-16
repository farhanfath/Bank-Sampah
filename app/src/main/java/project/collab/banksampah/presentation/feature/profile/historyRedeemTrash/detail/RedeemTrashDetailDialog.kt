package project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import project.collab.banksampah.R
import project.collab.banksampah.domain.model.response.trash_exchange.ListOfTrash
import project.collab.banksampah.domain.model.response.trash_exchange.TrashExchangeHistory
import project.collab.banksampah.presentation.components.base.BaseDialog
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.detail.SelectableText
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_12
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_24
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8
import project.collab.banksampah.presentation.utils.toFormattedDateTime
import java.text.NumberFormat
import java.util.Locale
import kotlin.collections.forEach


@Composable
fun TrashExchangeDetailDialog(
    isVisible: Boolean,
    trashExchangeData: TrashExchangeHistory?,
    onDismiss: () -> Unit
) {
    BaseDialog(
        isVisible = isVisible,
        onDismiss = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        trashExchangeData?.let { data ->
            TrashExchangeDetailContent(
                data = data,
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
private fun TrashExchangeDetailContent(
    data: TrashExchangeHistory,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing_20)
            .wrapContentHeight()
            .heightIn(max = 600.dp), // Batasi tinggi maksimal
        shape = RoundedCornerShape(Spacing_16),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Spacing_8
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()) // Enable scroll untuk konten panjang
        ) {
            // Header Section
            TrashDetailDialogHeader(
                title = "Detail Penukaran Sampah",
                onCloseClick = onDismiss
            )

            Column(
                modifier = Modifier.padding(horizontal = Spacing_24)
            ) {
                Spacer(modifier = Modifier.height(Spacing_20))

                // Transaction Info Section
                TrashTransactionInfoSection(data = data)

                Spacer(modifier = Modifier.height(Spacing_20))

                // Divider
                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(Spacing_20))

                // Exchange Details Section
                TrashExchangeInfoSection(data = data)

                Spacer(modifier = Modifier.height(Spacing_20))

                // Divider
                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp
                )

                Spacer(modifier = Modifier.height(Spacing_20))

                // Trash List Section
                TrashListSection(trashList = data.listOfTrash)

                Spacer(modifier = Modifier.height(Spacing_24))
            }
        }
    }
}

@Composable
private fun TrashDetailDialogHeader(
    title: String,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing_24,
                vertical = Spacing_20
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onCloseClick,
            modifier = Modifier.size(Spacing_24)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Gray,
                modifier = Modifier.size(Size_20)
            )
        }
    }
}

@Composable
private fun TrashTransactionInfoSection(
    data: TrashExchangeHistory
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_16)
    ) {
        Text(
            text = "Informasi Transaksi",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing_12)
        ) {
            TrashDetailInfoItem(
                label = "Kode Penukaran",
                value = data.exchangeCode,
                isSelectable = true
            )

            TrashDetailInfoItem(
                label = "Tanggal Penukaran",
                value = data.exchangeDate.toFormattedDateTime()
            )

            TrashDetailInfoItem(
                label = "Total Poin Diperoleh",
                value = "${data.totalPoint} Poin",
                valueColor = PrimaryGreen,
                showPointIcon = true
            )
        }
    }
}

@Composable
private fun TrashExchangeInfoSection(
    data: TrashExchangeHistory
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_16)
    ) {
        Text(
            text = "Informasi Penukaran",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing_12)
        ) {
            TrashDetailInfoItem(
                label = "Cabang BSU",
                value = data.bsuBranchName
            )

            TrashDetailInfoItem(
                label = "Kader",
                value = data.kaderName
            )

            TrashDetailInfoItem(
                label = "Jumlah Item Sampah",
                value = "${data.listOfTrash.size} Item"
            )
        }
    }
}

@Composable
private fun TrashListSection(
    trashList: List<ListOfTrash>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_16)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Detail Sampah",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            Surface(
                shape = RoundedCornerShape(Spacing_12),
                color = PrimaryGreen.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "${trashList.size} Item",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = PrimaryGreen,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(
                        horizontal = Spacing_8,
                        vertical = Spacing_4
                    )
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing_12)
        ) {
            trashList.forEach { trash ->
                TrashItemCard(trash = trash)
            }
        }
    }
}

@Composable
private fun TrashItemCard(
    trash: ListOfTrash
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Spacing_12),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing_16),
            verticalArrangement = Arrangement.spacedBy(Spacing_12)
        ) {
            // Header item sampah
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = trash.trashType,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    shape = RoundedCornerShape(Spacing_8),
                    color = PrimaryGreen.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "Rp ${trash.totalPrice.formatCurrency()}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = PrimaryGreen,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(
                            horizontal = Spacing_8,
                            vertical = Spacing_4
                        )
                    )
                }
            }

            // Detail item sampah
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TrashItemDetail(
                    label = "Jumlah",
                    value = "${trash.amount} ${trash.unit}"
                )

                TrashItemDetail(
                    label = "Harga/Unit",
                    value = "Rp ${trash.unitPrice.formatCurrency()}"
                )
            }
        }
    }
}

@Composable
private fun TrashItemDetail(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_4)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        )
    }
}

@Composable
private fun TrashDetailInfoItem(
    label: String,
    value: String,
    valueColor: Color = Color.Black,
    showPointIcon: Boolean = false,
    isSelectable: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.weight(0.4f)
        )

        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showPointIcon) {
                BaseImage(
                    image = R.drawable.ic_point_second,
                    modifier = Modifier
                        .width(Spacing_16)
                        .height(Size_12)
                )

                Spacer(modifier = Modifier.width(Spacing_4))
            }

            if (isSelectable) {
                SelectableText(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = valueColor,
                        textAlign = TextAlign.End
                    )
                )
            } else {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = valueColor,
                        textAlign = TextAlign.End
                    )
                )
            }
        }
    }
}

// Extension function untuk format currency
fun Int.formatCurrency(): String {
    return NumberFormat.getNumberInstance(Locale("id", "ID")).format(this)
}