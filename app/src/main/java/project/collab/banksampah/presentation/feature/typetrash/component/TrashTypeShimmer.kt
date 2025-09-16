package project.collab.banksampah.presentation.feature.typetrash.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Spacing_10

@Composable
fun TrashTypeCardShimmer() {
    Card(
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
            // Image Shimmer
            BaseShimmer(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            // Content Shimmer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Title & Category
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BaseShimmer(
                        modifier = Modifier
                            .width(120.dp)
                            .height(20.dp)
                    )

                    BaseShimmer(
                        modifier = Modifier
                            .width(40.dp)
                            .height(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Description
                BaseShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
                BaseShimmer(
                    modifier = Modifier
                        .width(200.dp)
                        .height(16.dp)
                )

                // Price
                BaseShimmer(
                    modifier = Modifier
                        .width(100.dp)
                        .height(18.dp)
                )
            }
        }
    }
}

