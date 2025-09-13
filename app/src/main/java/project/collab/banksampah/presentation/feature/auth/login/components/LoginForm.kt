package project.collab.banksampah.presentation.feature.auth.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.PasswordTextField
import project.collab.banksampah.presentation.components.base.PhoneTextField
import project.collab.banksampah.domain.model.request.LoginRequest
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16

@Composable
fun LoginForm(
    loginData: LoginRequest,
    onDataChange: (LoginRequest) -> Unit,
    onLoginClick: () -> Unit,
    onGoToRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing_16),
        modifier = Modifier
            .padding(horizontal = Spacing_16)
            .fillMaxWidth()
    ) {
        Text(
            text = "Login Akunmu Disini!",
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Left
            )
        )

        PhoneTextField(
            value = loginData.phoneNumber,
            onValueChange = {
                onDataChange(loginData.copy(phoneNumber = it))
            },
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        PasswordTextField(
            value = loginData.password,
            onValueChange = {
                onDataChange(loginData.copy(password = it))
            },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        val buttonCondition = loginData.phoneNumber.isNotBlank() && loginData.password.isNotBlank()
        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Masuk",
            enabled = buttonCondition,
            onClick = onLoginClick,
            shape = RoundedCornerShape(Spacing_12)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = "Belum Punya Akun? ",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Left
                    )
                )
                Text(
                    modifier = Modifier.clickable(onClick = onGoToRegisterClick),
                    text = "Daftar",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textAlign = TextAlign.Left,
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Text(
                modifier = Modifier.clickable(onClick = onForgotPasswordClick),
                text = "Lupa Password?",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Left,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}