package project.collab.banksampah.presentation.feature.schedule.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_8

// Schedule Card Shimmer
@Composable
fun ScheduleCardShimmer() {
    Card(
        modifier = Modifier
            .padding(horizontal = Spacing_10, vertical = Spacing_8)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header shimmer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    BaseShimmer(
                        modifier = Modifier
                            .width(180.dp)
                            .height(18.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    BaseShimmer(
                        modifier = Modifier
                            .width(100.dp)
                            .height(14.dp)
                    )
                }

                BaseShimmer(
                    modifier = Modifier
                        .width(60.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Time section shimmer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                repeat(2) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF8F9FA)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            BaseShimmer(
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(16.dp)
                            )
                            BaseShimmer(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(20.dp)
                            )
                            BaseShimmer(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(12.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Footer shimmer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseShimmer(
                    modifier = Modifier
                        .width(100.dp)
                        .height(12.dp)
                )

                BaseShimmer(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
}