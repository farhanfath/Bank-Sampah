package project.collab.banksampah.presentation.feature.article_lobby.component.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.Size_2
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_200
import project.collab.banksampah.presentation.theme.Size_80
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_15
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun CarouselLoadingState(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // HorizontalPager shimmer with same structure as original
        Row(
            horizontalArrangement = Arrangement.spacedBy(Spacing_16),
            modifier = Modifier
                .height(Size_200)
        ) {
            repeat(3) { // Show 2 shimmer cards like visible pages
                ArticleCardShimmer(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing_16))

        // Dots indicator shimmer - matches DotsIndicator
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spacing_4),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            items(3) { // Show 3 dots shimmer
                BaseShimmer(
                    modifier = Modifier
                        .size(Spacing_8) // Match dotSize
                        .clip(CircleShape)
                )
            }
        }
    }
}

// Shimmer that matches ArticleCard structure
@Composable
fun ArticleCardShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(Spacing_12), // Match ArticleCard shape
        elevation = CardDefaults.cardElevation(defaultElevation = Spacing_4) // Match elevation
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image shimmer
            BaseShimmer(
                modifier = Modifier.fillMaxSize()
            )

            // Gradient overlay shimmer (subtle)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Gray.copy(alpha = 0.3f)
                            ),
                            startY = 100f
                        )
                    )
            )

            // Content shimmer - matches text position
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(Spacing_16) // Match ArticleCard padding
            ) {
                // Title shimmer (3 lines max like original)
                BaseShimmer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Size_20) // Title line height
                )

                Spacer(modifier = Modifier.height(Size_2))

                BaseShimmer(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(Size_20)
                )

                Spacer(modifier = Modifier.height(Size_2))

                BaseShimmer(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(Size_20)
                )

                Spacer(modifier = Modifier.height(Spacing_4)) // Match spacing

                // Timestamp shimmer
                BaseShimmer(
                    modifier = Modifier
                        .width(Size_80) // Typical timestamp width
                        .height(Spacing_15) // Body small text height
                )
            }
        }
    }
}



