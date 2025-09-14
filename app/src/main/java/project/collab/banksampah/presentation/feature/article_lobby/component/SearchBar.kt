package project.collab.banksampah.presentation.feature.article_lobby.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_16

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
            .padding(Spacing_16)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = "Cari Artikel...",
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (searchQuery.isNotBlank()) {
                IconButton(onClick = onClearSearch) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear search",
                        tint = Color.Gray
                    )
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryGreen,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        )
    )
//    Surface(
//        color = Color.White,
//        modifier = modifier
//            .border(
//                width = Size_1,
//                color = PrimaryGreen,
//                shape = RoundedCornerShape(Spacing_15)
//            )
//            .graphicsLayer {
//                shape = RoundedCornerShape(Spacing_15)
//                clip = true
//            }
//            .clickable {
//                onSearchClick()
//            }
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = Spacing_16, vertical = Spacing_12),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search Icon",
//                    tint = MaterialTheme.colorScheme.onSurface
//                )
//
//                Spacer(modifier = Modifier.width(Spacing_8))
//
//                Text(
//                    text = "Cari Artikel",
//                    color = MaterialTheme.colorScheme.onSurface,
//                    style = MaterialTheme.typography.labelMedium.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = PrimaryGrey
//                    ),
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
//            }
//        }
//    }
}