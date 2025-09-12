package project.collab.banksampah.presentation.feature.profile.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.domain.model.request.ResetPassRequest
import project.collab.banksampah.domain.model.request.UserRequest
import project.collab.banksampah.presentation.components.base.BaseHeader
import project.collab.banksampah.presentation.components.base.rememberVisibilityState
import project.collab.banksampah.presentation.feature.profile.user.components.ContactUsSection
import project.collab.banksampah.presentation.feature.profile.user.components.LogoutDialog
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.ProfileImageBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.ProfileSettingsSection
import project.collab.banksampah.presentation.feature.profile.user.components.UserProfileSection
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.FaqBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.ResetPasswordBottomSheet
import project.collab.banksampah.presentation.feature.profile.user.components.bottomsheet.UserSettingBottomSheet
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_4
import project.collab.banksampah.presentation.theme.Spacing_50
import project.collab.banksampah.presentation.utils.hide
import project.collab.banksampah.presentation.utils.show

@Composable
fun ProfileUserScreen(
    userViewModel: UserViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    var editUserData by remember { mutableStateOf(UserRequest()) }
    var resetPassData by remember { mutableStateOf(ResetPassRequest()) }

    val profileImageChangeBottomSheetState = rememberVisibilityState()
    val logoutDialogState = rememberVisibilityState()
    val editUserBottomSheetState = rememberVisibilityState()
    val resetPassBottomSheetState = rememberVisibilityState()
    val faqBottomSheetState = rememberVisibilityState()

    val userDataState by userViewModel.userDataState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userViewModel.getUserData()
    }

    Column(
        modifier = Modifier
            .padding(horizontal = Spacing_16, vertical = Spacing_4)
            .fillMaxSize(),
    ) {
        BaseHeader(
            title = "Profil Pengguna",
            onBackClick = onBackClick
        )

        if (userDataState.userData != null) {
            UserProfileSection(
                userData = userDataState.userData!!,
                onImageChangeClick = profileImageChangeBottomSheetState::show,
                onLogoutClick = logoutDialogState::show
            )
        }

        Spacer(modifier = Modifier.size(Spacing_50))

        ProfileSettingsSection(
            onSettingClick = editUserBottomSheetState::show,
            onChangePassClick = resetPassBottomSheetState::show,
            onFaqClick = faqBottomSheetState::show
        )

        Spacer(modifier = Modifier.size(Spacing_50))

        ContactUsSection()

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
            onEditUserSave = {}
        )

        ResetPasswordBottomSheet(
            isVisible = resetPassBottomSheetState.value,
            onDismiss = resetPassBottomSheetState::hide,
            resetPassData = resetPassData,
            onDataChange = { resetPassData = it },
            onResetPassSave = {}
        )

        FaqBottomSheet(
            isVisible = faqBottomSheetState.value,
            onDismiss = faqBottomSheetState::hide
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileUserScreen() {
    ProfileUserScreen(
        onBackClick = {}
    )
}