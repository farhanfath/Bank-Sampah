package project.collab.banksampah.presentation.feature.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.login.components.LoginForm
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.presentation.components.LoadingOverlay
import project.collab.banksampah.presentation.theme.Spacing_150

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
    onGoToRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var loginData by remember { mutableStateOf(LoginRequest()) }

    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(loginState.isLoginSuccess) {
        if (loginState.isLoginSuccess) {
            onLoginSuccess()
        }
    }
    LoadingOverlay(
        isVisible = loginState.isLoading
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AuthHeader(
                modifier = Modifier.size(Spacing_150),
                onBackClick = onBackClick)

            LoginForm(
                loginData = loginData,
                onDataChange = { loginData = it },
                onLoginClick = { viewModel.login(loginData) },
                onGoToRegisterClick = onGoToRegisterClick,
                onForgotPasswordClick = onForgotPasswordClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = {},
        onGoToRegisterClick = {},
        onForgotPasswordClick = {},
        onBackClick = {}
    )
}