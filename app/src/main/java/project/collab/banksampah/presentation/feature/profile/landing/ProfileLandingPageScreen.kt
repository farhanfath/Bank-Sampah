package project.collab.banksampah.presentation.feature.profile.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_30
import project.collab.banksampah.presentation.theme.Spacing_50

/**
 * screen when user haven't login when on the profile section
 */
@Composable
fun ProfileLandingPageScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_30)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selamat Datang di Bank Sampah",
            style = MaterialTheme.typography.headlineLarge.copy(
                textAlign = TextAlign.Center,
                color = PrimaryGreen
            ),
        )

        Spacer(modifier = Modifier.size(Spacing_50))

        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Masuk",
            onClick = onLoginClick
        )

        Spacer(modifier = Modifier.size(Spacing_20))

        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Daftar",
            onClick = onRegisterClick
        )
        
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileLandingPageScreenPreview() {
    ProfileLandingPageScreen(
        onLoginClick = {},
        onRegisterClick = {}
    )
}