package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.AccentWhite
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_15

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    containerColor: Color = PrimaryGreen,
    disabledContainerColor: Color = PrimaryGreen.copy(alpha = 0.6f),
    shape: RoundedCornerShape = RoundedCornerShape(Spacing_15),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !isLoading, // Disable button when loading
        shape = shape,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = if (enabled && !isLoading) containerColor else disabledContainerColor,
            contentColor = AccentWhite,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = AccentWhite.copy(alpha = 0.7f)
        )
    ) {
        if (isLoading) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = AccentWhite
                )
                if (text.isNotBlank()) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = text,
                        style = textStyle
                    )
                }
            }
        } else {
            Text(
                text = text,
                style = textStyle
            )
        }
    }
}