package project.collab.banksampah.presentation.feature.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.domain.model.request.user.RegisterRequest
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.register.components.RegisterForm

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onGoToLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var registerData by remember { mutableStateOf(RegisterRequest()) }

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