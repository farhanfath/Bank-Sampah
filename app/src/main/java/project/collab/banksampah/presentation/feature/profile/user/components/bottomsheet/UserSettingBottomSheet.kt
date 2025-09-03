package project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import project.collab.banksampah.domain.model.request.user.UserRequest
import project.collab.banksampah.presentation.components.base.AddressTextField
import project.collab.banksampah.presentation.components.base.BaseBottomSheet
import project.collab.banksampah.presentation.components.base.BaseButton
import project.collab.banksampah.presentation.components.base.BaseTextField
import project.collab.banksampah.presentation.components.base.NikTextField
import project.collab.banksampah.presentation.components.base.PhoneTextField
import project.collab.banksampah.presentation.theme.AccentGrey
import project.collab.banksampah.presentation.theme.Size_20
import project.collab.banksampah.presentation.theme.Spacing_10
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_20
import project.collab.banksampah.presentation.theme.Spacing_4

@Composable
fun UserSettingBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    editProfileData: UserRequest,
    onDataChange: (UserRequest) -> Unit,
    onEditUserSave: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BaseBottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss,
        isFullExpand = true
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(Spacing_20)
                .fillMaxWidth()
        ) {
            Text(
                text = "Akun Anda",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            LazyColumn {
                item {
                    EditUserInputSection(
                        hint = "Nama"
                    ) {
                        BaseTextField(
                            hint = "Nama",
                            value = editProfileData.name,
                            onValueChange = {
                                onDataChange(editProfileData.copy(name = it))
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
                    }
                }

                item {
                    EditUserInputSection(
                        hint = "NIK"
                    ) {
                        NikTextField(
                            value = editProfileData.nik,
                            onValueChange = {
                                onDataChange(editProfileData.copy(nik = it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )
                    }
                }

                item {
                    EditUserInputSection(
                        hint = "Nomor HP"
                    ) {
                        PhoneTextField(
                            value = editProfileData.phoneNumber,
                            onValueChange = {
                                onDataChange(editProfileData.copy(phoneNumber = it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )
                    }
                }

                item {
                    EditUserInputSection(
                        hint = "Alamat"
                    ) {
                        AddressTextField(
                            value = editProfileData.address,
                            onValueChange = {
                                onDataChange(editProfileData.copy(address = it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = Spacing_10)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.size(Spacing_16))

                        BaseButton(
                            modifier = Modifier.align(Alignment.End),
                            text = "Simpan",
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            onClick = onEditUserSave
                        )

                        Spacer(modifier = Modifier.size(Spacing_20))
                    }
                }
            }
        }
    }
}


@Composable
private fun EditUserInputSection(
    hint: String,
    content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = Spacing_10),
        verticalArrangement = Arrangement.spacedBy(Spacing_4),
    ) {
        Text(
            text = hint,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        content()

        Spacer(modifier = Modifier.size(Spacing_4))
    }
}