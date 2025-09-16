package project.collab.banksampah.presentation.feature.profile.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.request.EditPassRequest
import project.collab.banksampah.domain.model.request.UserRequest
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.profile.user.components.LogoutDialog
import project.collab.banksampah.presentation.feature.profile.user.components.ProfileErrorSection
import project.collab.banksampah.presentation.feature.profile.user.components.ProfileSettingsSection
import project.collab.banksampah.presentation.feature.profile.user.components.UserProfileSection
import project.collab.banksampah.presentation.feature.profile.user.components.UserProfileSectionShimmer
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.FaqBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.ProfileImageBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.EditPasswordBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.UserSettingBottomSheet
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_50
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.replaceIfNull
import project.collab.banksampah.presentation.utils.show

@Composable
fun ProfileUserScreen(
    userViewModel: UserViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    var editUserData by remember { mutableStateOf(UserRequest()) }
    var editPassData by remember { mutableStateOf(EditPassRequest()) }

    val profileImageChangeBottomSheetState = rememberVisibilityState()
    val logoutDialogState = rememberVisibilityState()
    val editUserBottomSheetState = rememberVisibilityState()
    val resetPassBottomSheetState = rememberVisibilityState()
    val faqBottomSheetState = rememberVisibilityState()

    val userDataState by userViewModel.userDataState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userViewModel.getUserData()
    }

    LaunchedEffect(userDataState.userData) {
        userDataState.userData?.let { user ->
            editUserData = UserRequest(
                name = user.name,
                nik = user.nik,
                address = user.address
            )
        }
    }

    LaunchedEffect(userDataState.isEditSuccess, userDataState.isPasswordChanged,userDataState.isError) {
        when {
            userDataState.isEditSuccess -> {
                userDataState.message?.takeIf { it.isNotBlank() }?.let { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                editUserBottomSheetState.hide()

                if (userDataState.isPasswordChanged) {
                    editPassData = EditPassRequest()
                    resetPassBottomSheetState.hide()
                }
                resetPassBottomSheetState.hide()
            }

            userDataState.isError -> {
                userDataState.error?.takeIf { it.isNotBlank() }?.let { error ->
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }
                userViewModel.clearErrorStates()
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = it.calculateTopPadding(), horizontal = Spacing_16)
                .fillMaxSize(),
        ) {
            BaseHeader(
                title = "Profil Pengguna",
                onBackClick = onBackClick
            )

            when {
                userDataState.isLoading -> {
                    UserProfileSectionShimmer()
                }
                userDataState.userData != null -> {
                    userDataState.userData?.let { user ->
                        UserProfileSection(
                            userData = user,
                            onImageChangeClick = profileImageChangeBottomSheetState::show,
                            onLogoutClick = logoutDialogState::show
                        )
                    }
                }
                userDataState.isError -> {
                    ProfileErrorSection(
                        errorMessage = userDataState.error ?: "Terjadi kesalahan",
                        onRetry = userViewModel::getUserData
                    )
                }
            }

            Spacer(modifier = Modifier.size(Spacing_50))

            ProfileSettingsSection(
                onSettingClick = editUserBottomSheetState::show,
                onChangePassClick = resetPassBottomSheetState::show,
                onFaqClick = faqBottomSheetState::show
            )

            /**
             * Other section handler
             * like bottom sheet and dialog
             */
            ProfileImageBottomSheet(
                isVisible = profileImageChangeBottomSheetState.value,
                onDismiss = profileImageChangeBottomSheetState::hide
            )

            LogoutDialog(
                isVisible = logoutDialogState.value,
                onDismiss = logoutDialogState::hide,
                onLogoutClick = {
                    userViewModel.logout()
                    onBackClick()
                }
            )

            UserSettingBottomSheet(
                isVisible = editUserBottomSheetState.value,
                onDismiss = editUserBottomSheetState::hide,
                editProfileData = editUserData,
                onDataChange = { editUserData = it },
                onEditUserSave = {
                    userViewModel.editUserData(editUserData)
                },
                userPhoneNumber = userDataState.userData?.number.replaceIfNull(),
                isLoading = userDataState.isLoading
            )

            EditPasswordBottomSheet(
                isVisible = resetPassBottomSheetState.value,
                onDismiss = resetPassBottomSheetState::hide,
                editPassData = editPassData,
                onDataChange = { editPassData = it },
                onResetPassSave = {
                    userViewModel.editPasswordUser(editPassData)
                },
                isLoading = userDataState.isLoading
            )

            FaqBottomSheet(
                isVisible = faqBottomSheetState.value,
                onDismiss = faqBottomSheetState::hide
            )
        }
    }
}