package project.collab.banksampah.presentation.navigation.host

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import project.collab.banksampah.presentation.components.CustomBottomNavigationBar
import project.collab.banksampah.presentation.components.CustomTopBar
import project.collab.banksampah.presentation.feature.lobby.LobbyScreen
import project.collab.banksampah.presentation.feature.profile.homeprofile.HomeProfileScreen
import project.collab.banksampah.presentation.feature.profile.landing.ProfileLandingPageScreen
import project.collab.banksampah.presentation.navigation.extensions.asHomeProfileNavigator
import project.collab.banksampah.presentation.navigation.route.NavRoute

@Composable
fun HomeBottomSheetHost(
    navController: NavHostController = rememberNavController(),
    mainNavController: NavHostController
) {
    val homeProfileNavigator = remember { mainNavController.asHomeProfileNavigator() }

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
                LobbyScreen()
            }

            composable<NavRoute.Home.Gallery> {
                LobbyScreen()
            }

            composable<NavRoute.Home.TypeTrash> {
                LobbyScreen()
            }

            composable<NavRoute.Home.Schedule> {
                ProfileLandingPageScreen(
                    onLoginClick = {},
                    onRegisterClick = {}
                )
            }

            composable<NavRoute.Home.Profile> {
                HomeProfileScreen(
                    navigator = homeProfileNavigator
                )
            }

            composable<NavRoute.Home.Forum> {
                LobbyScreen()
            }
        }
    }

}