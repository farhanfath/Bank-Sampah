package project.collab.banksampah.presentation.feature.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.presentation.components.LoadingOverlay
import project.collab.banksampah.presentation.feature.auth.components.AuthHeader
import project.collab.banksampah.presentation.feature.auth.register.components.RegisterForm

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    onRegisterSuccess: () -> Unit,
    onGoToLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    var registerData by remember { mutableStateOf(RegisterRequest()) }
    val registerState by viewModel.registerState.collectAsStateWithLifecycle()

    LaunchedEffect(registerState.isRegisterSuccess) {
        if (registerState.isRegisterSuccess) {
            Toast.makeText(context, "${registerState.message}, silahkan lanjutkan untuk login", Toast.LENGTH_SHORT).show()
            onRegisterSuccess()
        }
    }

    LaunchedEffect(registerState.isRegisterFailed) {
        if (registerState.isRegisterFailed) {
            val message = registerState.message
            if (!message.isNullOrBlank()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }



    LoadingOverlay(
        isVisible = registerState.isLoading
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AuthHeader(onBackClick = onBackClick)

            RegisterForm(
                registerData = registerData,
                onDataChange = { registerData = it },
                onRegisterClick = {
                    viewModel.register(registerData)
                },
                onGoToLoginClick = onGoToLoginClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterSuccess = {},
        onGoToLoginClick = {},
        onBackClick = {}
    )
}