package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.AccentWhite
import project.collab.banksampah.presentation.theme.PrimaryGreen

@Composable
fun BaseButton(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(15.dp),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = PrimaryGreen,
            contentColor = AccentWhite
        )
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}