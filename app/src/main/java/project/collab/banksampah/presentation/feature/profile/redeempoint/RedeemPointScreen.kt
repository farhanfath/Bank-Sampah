package project.collab.banksampah.presentation.feature.profile.redeempoint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.domain.model.request.RedeemPointRequest
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.feature.profile.redeempoint.components.RedeemPointForm
import project.collab.banksampah.presentation.feature.profile.redeempoint.components.TotalPointCard
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun RedeemPointScreen(
    onBackClick: () -> Unit
) {
    var redeemPointData by remember { mutableStateOf(RedeemPointRequest()) }

    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16, vertical = Spacing_4)
            .fillMaxSize(),
    ) {
        BaseHeader(
            title = "Penukaran Poin",
            onBackClick = onBackClick
        )

        TotalPointCard()

        Spacer(modifier = Modifier.size(Spacing_20))

        RedeemPointForm(
            redeemPointRequest = redeemPointData,
            onDataChange = { redeemPointData = it },
            onSubmit = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRedeemPointScreen() {
    RedeemPointScreen(
        onBackClick = {}
    )
}

