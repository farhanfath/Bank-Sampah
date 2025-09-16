package project.collab.banksampah.presentation.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Komponen reusable untuk copy text dengan feedback visual
 */
@Composable
fun CopyTextButton(
    textToCopy: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    icon: ImageVector = Icons.Default.ContentCopy,
    iconSize: Dp = 16.dp,
    buttonSize: Dp = 32.dp,
    iconTint: Color = Color(0xFF666666),
    successMessage: String = "Disalin ke clipboard",
    showFeedback: Boolean = true
) {
    val context = LocalContext.current
    var isCopied by remember { mutableStateOf(false) }

    LaunchedEffect(isCopied) {
        if (isCopied) {
            delay(2000) // Reset after 2 seconds
            isCopied = false
        }
    }

    IconButton(
        onClick = {
            copyTextToClipboard(
                context = context,
                text = textToCopy,
                label = label ?: "Text",
                showToast = showFeedback,
                successMessage = successMessage
            )
            isCopied = true
        },
        modifier = modifier.size(buttonSize)
    ) {
        Icon(
            imageVector = if (isCopied) Icons.Default.Check else icon,
            contentDescription = "Copy $label",
            tint = if (isCopied) Color(0xFF4CAF50) else iconTint,
            modifier = Modifier.size(iconSize)
        )
    }
}

/**
 * Komponen text yang bisa di-copy dengan tap
 */
@Composable
fun CopyableText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    label: String = "Text",
    successMessage: String = "Disalin ke clipboard",
    showCopyIcon: Boolean = true,
    copyIconTint: Color = Color(0xFF666666),
    showFeedback: Boolean = true
) {
    val context = LocalContext.current
    var isCopied by remember { mutableStateOf(false) }

    LaunchedEffect(isCopied) {
        if (isCopied) {
            delay(2000)
            isCopied = false
        }
    }

    Row(
        modifier = modifier.clickable {
            copyTextToClipboard(
                context = context,
                text = text,
                label = label,
                showToast = showFeedback,
                successMessage = successMessage
            )
            isCopied = true
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = text,
            style = style,
            modifier = Modifier.weight(1f, fill = false)
        )

        if (showCopyIcon) {
            Icon(
                imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                contentDescription = "Copy $label",
                tint = if (isCopied) Color(0xFF4CAF50) else copyIconTint,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

private fun copyTextToClipboard(
    context: Context,
    text: String,
    label: String,
    showToast: Boolean = true,
    successMessage: String = "Disalin ke clipboard"
) {
    try {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)

        if (showToast) {
            Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        if (showToast) {
            Toast.makeText(context, "Gagal menyalin teks", Toast.LENGTH_SHORT).show()
        }
    }
}