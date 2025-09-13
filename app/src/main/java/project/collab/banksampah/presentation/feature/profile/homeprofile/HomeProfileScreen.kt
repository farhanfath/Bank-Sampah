package project.collab.banksampah.presentation.feature.profile.homeprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.presentation.feature.profile.homeprofile.components.ProfileHeader
import project.collab.banksampah.presentation.feature.profile.homeprofile.components.ProfileMenu
import project.collab.banksampah.presentation.feature.profile.user.UserViewModel
import project.collab.banksampah.presentation.feature.profile.user.components.shimmer.ProfileHeaderShimmer
import project.collab.banksampah.presentation.navigation.navigator.HomeProfileNavigator
import project.collab.banksampah.presentation.theme.Spacing_16
import project.collab.banksampah.presentation.theme.Spacing_50

@Composable
fun HomeProfileScreen(
    navigator: HomeProfileNavigator,
    userViewModel: UserViewModel = koinViewModel()
) {
    val userDataState by userViewModel.userDataState.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.getUserData()
    }

    Column(
        modifier = Modifier
            .padding(Spacing_16)
            .fillMaxSize()
    ) {
        when {
            userDataState.isLoading -> {
                ProfileHeaderShimmer()
            }
            userDataState.isDataLoaded -> {
                ProfileHeader(
                    onUserProfileClick = navigator::navigateToUserProfile,
                    userName = userDataState.userData?.name ?: ""
                )
            }
        }

        Spacer(modifier = Modifier.size(Spacing_50))

        ProfileMenu(
            onRedeemPointClick = navigator::navigateToRedeemPoint,
            onHistoryRedeemPointClick = navigator::navigateToHistoryRedeemPoint,
            onHistoryRedeemTrashClick = navigator::navigateToHistoryRedeemTrash
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeProfileScreenPreview(

) {
    HomeProfileScreen(
        navigator = object : HomeProfileNavigator {
            override fun navigateToUserProfile() {}

            override fun navigateToRedeemPoint() {}

            override fun navigateToHistoryRedeemPoint() {}

            override fun navigateToHistoryRedeemTrash() {}
        }
    )
}