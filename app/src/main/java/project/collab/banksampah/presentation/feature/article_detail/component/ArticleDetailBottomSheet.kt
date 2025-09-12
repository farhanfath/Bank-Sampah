package project.collab.banksampah.presentation.feature.article_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import project.collab.banksampah.R
import project.collab.banksampah.presentation.components.base.StickyBaseBottomSheet
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_20

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailBottomSheet(
    date: String,
    description: String
) {
    StickyBaseBottomSheet(
        modifier = Modifier.padding(
            top = Size_100
        ),
        isVisible = true,
        isFullExpand = true,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(Spacing_20)
                .fillMaxWidth()
        ) {
            Text(
                text = "Detail Artikel",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.size(Spacing_10))

            Text(
                text = "15 Agustus 2025",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(
                    textAlign = TextAlign.End
                )
            )

            Spacer(modifier = Modifier.size(Spacing_10))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.placeholder_200_words),
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Justify,
                    color = Color.Black
                )
            )
        }
    }
}