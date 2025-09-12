package project.collab.banksampah.presentation.navigation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import project.collab.banksampah.presentation.components.CustomBottomNavigationBar
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.feature.article_lobby.LobbyScreen
import project.collab.banksampah.presentation.feature.gallery.GalleryScreen
import project.collab.banksampah.presentation.feature.profile.homeprofile.HomeProfileScreen
import project.collab.banksampah.presentation.feature.profile.landing.ProfileLandingPageScreen
import project.collab.banksampah.presentation.feature.profile.user.UserViewModel
import project.collab.banksampah.presentation.feature.schedule.ScheduleScreen
import project.collab.banksampah.presentation.feature.typetrash.TypeTrashScreen
import project.collab.banksampah.presentation.navigation.extensions.asHomeProfileNavigator
import project.collab.banksampah.presentation.navigation.extensions.navigateToArticleDetail
import project.collab.banksampah.presentation.navigation.extensions.navigateToLogin
import project.collab.banksampah.presentation.navigation.extensions.navigateToRegister
import project.collab.banksampah.presentation.navigation.route.NavRoute

@Composable
fun HomeBottomSheetHost(
    navController: NavHostController = rememberNavController(),
    mainNavController: NavHostController,
    userViewModel: UserViewModel = koinViewModel()
) {
    val homeProfileNavigator = remember { mainNavController.asHomeProfileNavigator() }

    val isLoggedIn by userViewModel.isLoggedIn.collectAsState(false)

    Scaffold(
        topBar = { CustomTopBar() },
        bottomBar = {
            CustomBottomNavigationBar(
                navController = navController
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = NavRoute.Home.Lobby
        ) {
            composable<NavRoute.Home.Lobby> {
                LobbyScreen(
                    onArticleClick = { article ->
                        mainNavController.navigateToArticleDetail(article)
                    }
                )
            }

            composable<NavRoute.Home.Gallery> {
                GalleryScreen(
                    onClick = {}
                )
            }

            composable<NavRoute.Home.TypeTrash> {
                TypeTrashScreen()
            }

            composable<NavRoute.Home.Schedule> {
                ScheduleScreen()
            }

            composable<NavRoute.Home.Profile> {
                if (isLoggedIn)  {
                    HomeProfileScreen(
                        navigator = homeProfileNavigator
                    )
                } else {
                    ProfileLandingPageScreen(
                        onLoginClick = mainNavController::navigateToLogin,
                        onRegisterClick = mainNavController::navigateToRegister
                    )
                }
            }
        }
    }

}