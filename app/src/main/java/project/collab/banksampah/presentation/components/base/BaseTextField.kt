package project.collab.banksampah.presentation.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import project.collab.banksampah.presentation.theme.AccentGrey
import project.collab.banksampah.presentation.theme.AccentWhite
import project.collab.banksampah.presentation.theme.PrimaryBlack
import project.collab.banksampah.presentation.theme.PrimaryGreen
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Spacing_12
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    hint: String,
    placeholder: String = "",
    value: String,
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    transformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(Spacing_12),
    readOnly: Boolean = false,
    contentPadding: PaddingValues = TextFieldDefaults.contentPaddingWithLabel()
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder.ifEmpty { hint },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = AccentGrey
                    )
                )
            },
            label = if (hint.isNotEmpty()) {
                {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            } else null,
            visualTransformation = transformation,
            singleLine = maxLines == 1,
            maxLines = if (maxLines == 1) 1 else maxLines,
            minLines = minLines,
            enabled = enabled,
            isError = isError,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            shape = shape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = AccentGrey,
                unfocusedTextColor = PrimaryBlack,
                unfocusedContainerColor = Color.White,
                unfocusedLabelColor = AccentGrey,
                unfocusedPlaceholderColor = AccentGrey,

                focusedTextColor = PrimaryBlack,
                focusedBorderColor = PrimaryGreen,
                focusedContainerColor = Color.White,
                focusedLabelColor = PrimaryGreen,
                focusedPlaceholderColor = AccentGrey,

                errorBorderColor = Color.Red,
                errorTextColor = PrimaryBlack,
                errorContainerColor = Color.White,
                errorLabelColor = Color.Red,
                errorPlaceholderColor = AccentGrey,

                disabledBorderColor = AccentGrey.copy(alpha = 0.5f),
                disabledTextColor = AccentGrey,
                disabledContainerColor = AccentGrey.copy(alpha = 0.1f),
                disabledLabelColor = AccentGrey.copy(alpha = 0.5f),
                disabledPlaceholderColor = AccentGrey.copy(alpha = 0.5f)
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            readOnly = readOnly
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Red
                ),
                modifier = Modifier.padding(start = Spacing_16, top = Spacing_4)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseTextFieldPreview(modifier: Modifier = Modifier) {
    BaseTextField(
        hint = "Username",
        placeholder = "Enter your username",
        value = "",
        onValueChange = {},
        modifier = modifier
    )
}


/**
 * other specified textField
 */

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    hint: String = "Password",
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    BaseTextField(
        modifier = modifier,
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        errorMessage = errorMessage,
        transformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = keyboardActions,
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = AccentGrey
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password",
                tint = AccentGrey,
                modifier = Modifier.size(Size_20)
            )
        },
        enabled = enabled
    )
}

@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    hint: String = "Nomor Hp",
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BaseTextField(
        modifier = modifier,
        hint = hint,
        value = value,
        onValueChange = { newValue ->
            // Only allow digits
            if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                onValueChange(newValue)
            }
        },
        isError = isError,
        errorMessage = errorMessage,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone",
                tint = AccentGrey,
                modifier = Modifier.size(Size_20)
            )
        }
    )
}

@Composable
fun NikTextField(
    modifier: Modifier = Modifier,
    hint: String = "NIK",
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BaseTextField(
        modifier = modifier,
        hint = hint,
        value = value,
        onValueChange = { newValue ->
            // Only allow digits and max 16 characters
            if (newValue.all { it.isDigit() } && newValue.length <= 16) {
                onValueChange(newValue)
            }
        },
        isError = isError,
        errorMessage = errorMessage,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = keyboardActions,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.CreditCard,
                contentDescription = "NIK",
                tint = AccentGrey,
                modifier = Modifier.size(Size_20)
            )
        },
        enabled = enabled
    )
}

@Composable
fun AddressTextField(
    modifier: Modifier = Modifier,
    hint: String = "Alamat",
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BaseTextField(
        modifier = modifier,
        hint = hint,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        errorMessage = errorMessage,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = keyboardActions,
        maxLines = 3,
        minLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Address",
                tint = AccentGrey,
                modifier = Modifier.size(Size_20)
            )
        }
    )
}