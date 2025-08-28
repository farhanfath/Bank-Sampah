package project.collab.banksampah.presentation.feature.auth.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.R
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.register.components.RegisterForm
import project.collab.banksampah.presentation.feature.auth.register.state.RegisterData
import project.collab.banksampah.presentation.theme.Font_30
import project.collab.banksampah.presentation.theme.Font_32
import project.collab.banksampah.presentation.theme.PrimaryBlack
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_150
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_30

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onGoToLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var registerData by remember { mutableStateOf(RegisterData()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AuthHeader(onBackClick = onBackClick)

        RegisterForm(
            registerData = registerData,
            onDataChange = { registerData = it },
            onRegisterClick = onRegisterClick,
            onGoToLoginClick = onGoToLoginClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterClick = {},
        onGoToLoginClick = {},
        onBackClick = {}
    )
}