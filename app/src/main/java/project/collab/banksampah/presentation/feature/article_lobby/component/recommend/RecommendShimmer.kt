package project.collab.banksampah.presentation.feature.article_lobby.component.recommend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import project.collab.banksampah.presentation.components.base.BaseShimmer
import project.collab.banksampah.presentation.theme.PrimaryGrey
import project.collab.banksampah.presentation.theme.Size_10
import project.collab.banksampah.presentation.theme.Size_100
import project.collab.banksampah.presentation.theme.Size_12
import project.collab.banksampah.presentation.theme.Size_15
import project.collab.banksampah.presentation.theme.Size_150
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Size_80
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_8

@Composable
fun ShimmerItemRecommendationArticle() {
    Column(
        modifier = Modifier.padding(horizontal = Spacing_20)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Spacing_8)
        ) {
            // Article Image
            BaseShimmer(
                modifier = Modifier
                    .width(Size_150)
                    .height(Size_100)
            )

            Spacer(modifier = Modifier.width(Size_12))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(Size_80),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    BaseShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Size_20)
                    )
                    Spacer(modifier = Modifier.height(Spacing_4))
                    BaseShimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Size_15)
                    )
                }

                BaseShimmer(
                    modifier = Modifier
                        .width(Size_100)
                        .height(Size_10)
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = PrimaryGrey
        )
    }
}