package project.collab.banksampah.presentation.feature.profile.historyredeempoint.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import project.collab.banksampah.R
import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistory
import project.collab.banksampah.presentation.components.base.BaseDialog
import project.collab.banksampah.presentation.components.base.BaseImage
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.components.RedeemPointChipStatus
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.RedeemStatus
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.getRedeemStatus
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_12
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_24
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8
import project.collab.banksampah.presentation.utils.toFormattedDate


@Composable
fun RedeemPointDetailDialog(
    isVisible: Boolean,
    redeemPointData: RedeemPointHistory?,
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
        redeemPointData?.let { data ->
            RedeemPointDetailContent(
                data = data,
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
private fun RedeemPointDetailContent(
    data: RedeemPointHistory,
    onDismiss: () -> Unit
) {
    val redeemStatus = data.getRedeemStatus()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing_20)
            .wrapContentHeight(),
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
                .padding(Spacing_24)
        ) {
            // Header Section
            DetailDialogHeader(
                title = "Detail Penukaran Poin",
                onCloseClick = onDismiss
            )

            Spacer(modifier = Modifier.height(Spacing_20))

            // Status Section
            DetailStatusSection(
                status = redeemStatus,
                transactionCode = data.transactionCode
            )

            Spacer(modifier = Modifier.height(Spacing_20))

            // Details Section
            DetailInfoSection(data = data)

            Spacer(modifier = Modifier.height(Spacing_16))

            // Divider
            HorizontalDivider(
                color = Color.Gray.copy(alpha = 0.2f),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(Spacing_16))

            // Additional Info Section
            DetailAdditionalInfoSection(data = data)
        }
    }
}

@Composable
private fun DetailDialogHeader(
    title: String,
    onCloseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
private fun DetailStatusSection(
    status: RedeemStatus,
    transactionCode: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_12)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            )

            RedeemPointChipStatus(status = status)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kode Transaksi",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            )

            SelectableText(
                text = transactionCode,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
private fun DetailInfoSection(
    data: RedeemPointHistory
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
            DetailInfoItem(
                label = "Deskripsi",
                value = data.description
            )

            DetailInfoItem(
                label = "Permintaan Poin",
                value = "${data.pointExchangeRequest} Poin",
                valueColor = PrimaryGreen,
                showPointIcon = true
            )

            if (data.pointExchangeAccepted.isNotEmpty()) {
                DetailInfoItem(
                    label = "Poin Diterima",
                    value = "${data.pointExchangeAccepted} Poin",
                    valueColor = PrimaryGreen,
                    showPointIcon = true
                )
            }
        }
    }
}

@Composable
private fun DetailAdditionalInfoSection(
    data: RedeemPointHistory
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_12)
    ) {
        Text(
            text = "Informasi Tambahan",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing_8)
        ) {
            DetailInfoItem(
                label = "Tanggal Permintaan",
                value = data.requestDate.toFormattedDate()
            )

            if (data.acceptDate.isNotEmpty()) {
                DetailInfoItem(
                    label = "Tanggal Diproses",
                    value = data.acceptDate.toFormattedDate()
                )
            }

            if (data.editor.isNotEmpty()) {
                DetailInfoItem(
                    label = "Diproses oleh",
                    value = data.editor
                )
            }
        }
    }
}

@Composable
private fun DetailInfoItem(
    label: String,
    value: String,
    valueColor: Color = Color.Black,
    showPointIcon: Boolean = false
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

@Composable
fun SelectableText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Text(
        text = text,
        style = style,
        modifier = modifier.clickable {
            // Copy ke clipboard
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Transaction Code", text)
            clipboard.setPrimaryClip(clip)
        }
    )
}