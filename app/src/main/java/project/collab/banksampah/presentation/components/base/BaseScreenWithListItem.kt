package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun <T> BaseScreenWithListItem(
    onBackClick: () -> Unit,
    title: String,
    subTitle: String,
    itemList: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16, vertical = Spacing_4)
            .fillMaxSize(),
    ) {
        BaseHeader(
            title = title,
            textStyle = MaterialTheme.typography.headlineSmall.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.size(Spacing_10))

        BaseTitleSection(
            title = subTitle
        )

        Spacer(modifier = Modifier.size(Spacing_10))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing_10)
        ) {
            items(itemList) { data ->
                itemContent(data)
            }
        }
    }
}