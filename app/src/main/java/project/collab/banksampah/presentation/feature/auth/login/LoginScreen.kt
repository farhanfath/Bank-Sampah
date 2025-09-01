package project.collab.banksampah.presentation.feature.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.login.components.LoginForm
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.presentation.theme.Spacing_150

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onGoToRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var loginData by remember { mutableStateOf(LoginRequest()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AuthHeader(
            modifier = Modifier.size(Spacing_150),
            onBackClick = onBackClick)

        LoginForm(
            loginData = loginData,
            onDataChange = { loginData = it },
            onLoginClick = onLoginClick,
            onGoToRegisterClick = onGoToRegisterClick,
            onForgotPasswordClick = onForgotPasswordClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = {},
        onGoToRegisterClick = {},
        onForgotPasswordClick = {},
        onBackClick = {}
    )
}