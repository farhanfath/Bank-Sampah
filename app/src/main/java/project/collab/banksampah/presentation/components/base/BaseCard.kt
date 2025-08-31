package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Size_3
import project.collab.banksampah.presentation.theme.Spacing_15
import project.collab.banksampah.presentation.theme.Spacing_20

@Composable
fun BaseCard(
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick?.invoke() }),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(Spacing_15),
        border = BorderStroke(width = Size_3, color = PrimaryGreen),
        elevation = CardDefaults.cardElevation(defaultElevation = Size_2)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = Spacing_15)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseCardPreview() {
    BaseCard(
        onClick = {},
        content = {
            Text(
                modifier = Modifier.padding(Spacing_20),
                text = "Hello World!"
            )
        }
    )
}