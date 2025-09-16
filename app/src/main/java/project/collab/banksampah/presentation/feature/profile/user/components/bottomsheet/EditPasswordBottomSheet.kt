package project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import project.collab.banksampah.domain.model.request.EditPassRequest
import project.collab.banksampah.presentation.components.base.BaseBottomSheet
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.PasswordTextField
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20

@Composable
fun EditPasswordBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    editPassData: EditPassRequest,
    onDataChange: (EditPassRequest) -> Unit,
    onResetPassSave: () -> Unit,
    isLoading: Boolean = false
) {
    val focusManager = LocalFocusManager.current

    BaseBottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(Spacing_20)
                .fillMaxWidth()
        ) {
            Text(
                text = "Ganti Password",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            LazyColumn {
                item {
                    PasswordTextField(
                        hint = "Password Lama",
                        value = editPassData.oldPassword,
                        onValueChange = {
                            onDataChange(editPassData.copy(oldPassword = it))
                        },
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        enabled = !isLoading
                    )

                    Spacer(modifier = Modifier.size(Spacing_10))
                }

                item {
                    PasswordTextField(
                        hint = "Password Baru",
                        value = editPassData.newPassword,
                        onValueChange = {
                            onDataChange(editPassData.copy(newPassword = it))
                        },
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        enabled = !isLoading
                    )

                    Spacer(modifier = Modifier.size(Spacing_10))
                }

                item {
                    PasswordTextField(
                        hint = "Confirm Password",
                        value = editPassData.confirmPassword,
                        onValueChange = {
                            onDataChange(editPassData.copy(confirmPassword = it))
                        },
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        enabled = !isLoading
                    )
                }

                item {
                    val isFormValid = editPassData.oldPassword.isNotBlank() &&
                            editPassData.newPassword.isNotBlank() &&
                            editPassData.confirmPassword.isNotBlank()

                    val buttonEnabled = isFormValid && !isLoading

                    Column(
                        modifier = Modifier
                            .padding(horizontal = Spacing_10)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.size(Spacing_16))

                        BaseButton(
                            modifier = Modifier.align(Alignment.End),
                            text = if (isLoading) "Menyimpan..." else "Simpan",
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            onClick = onResetPassSave,
                            enabled = buttonEnabled
                        )

                        Spacer(modifier = Modifier.size(Spacing_20))
                    }
                }
            }
        }
    }
}