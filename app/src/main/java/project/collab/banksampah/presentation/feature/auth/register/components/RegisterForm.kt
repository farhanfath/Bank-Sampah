package project.collab.banksampah.presentation.feature.auth.register.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.domain.model.request.RegisterRequest
import project.collab.banksampah.presentation.components.base.AddressTextField
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.BaseTextField
import project.collab.banksampah.presentation.components.base.NikTextField
import project.collab.banksampah.presentation.components.base.PasswordTextField
import project.collab.banksampah.presentation.components.base.PhoneTextField
import project.collab.banksampah.presentation.theme.AccentGrey
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_30

@Composable
fun RegisterForm(
    registerData: RegisterRequest,
    onDataChange: (RegisterRequest) -> Unit,
    onRegisterClick: () -> Unit,
    onGoToLoginClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing_16)
    ) {
        Text(
            text = "Buat Akunmu Disini!",
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Left
            )
        )

        BaseTextField(
            hint = "Nama",
            value = registerData.name,
            onValueChange = {
                onDataChange(registerData.copy(name = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name",
                    tint = AccentGrey,
                    modifier = Modifier.size(Size_20)
                )
            }
        )

        NikTextField(
            value = registerData.nik,
            onValueChange = {
                onDataChange(registerData.copy(nik = it))
            },
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        PhoneTextField(
            value = registerData.phoneNumber,
            onValueChange = {
                onDataChange(registerData.copy(phoneNumber = it))
            },
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        AddressTextField(
            value = registerData.address,
            onValueChange = {
                onDataChange(registerData.copy(address = it))
            },
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        BaseTextField(
            hint = "Cabang BSU",
            value = registerData.bsuBranchName,
            onValueChange = {
                onDataChange(registerData.copy(bsuBranchName = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Branch",
                    tint = AccentGrey,
                    modifier = Modifier.size(Size_20)
                )
            }
        )

        BaseTextField(
            hint = "Kode Cabang BSU",
            value = registerData.bsuBranchCode,
            onValueChange = {
                onDataChange(registerData.copy(bsuBranchCode = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = "Branch",
                    tint = AccentGrey,
                    modifier = Modifier.size(Size_20)
                )
            }
        )

        PasswordTextField(
            value = registerData.password,
            onValueChange = {
                onDataChange(registerData.copy(password = it))
            },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        val buttonCondition = registerData.name.isNotBlank() && registerData.nik.isNotBlank() && registerData.phoneNumber.isNotBlank() && registerData.address.isNotBlank() && registerData.bsuBranchName.isNotBlank() && registerData.password.isNotBlank()
        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Daftar",
            enabled = buttonCondition,
            onClick = onRegisterClick,
            shape = RoundedCornerShape(Spacing_12)
        )

        Row {
            Text(
                text = "Sudah Punya Akun? ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Left
                )
            )
            Text(
                modifier = Modifier.clickable(onClick = onGoToLoginClick),
                text = "Masuk",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Left,
                    color = PrimaryGreen,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )
        }

        Spacer(modifier = Modifier.size(Spacing_30))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterFormPreview() {
    Column {
        RegisterForm(
            registerData = RegisterRequest(),
            onDataChange = {},
            onRegisterClick = {},
            onGoToLoginClick = {}
        )
    }
}