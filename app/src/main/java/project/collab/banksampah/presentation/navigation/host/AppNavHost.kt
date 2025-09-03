package project.collab.banksampah.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import project.collab.banksampah.getHistoryRedeemPointResponse
import project.collab.banksampah.getRedeemTrashHistoryResponse
import project.collab.banksampah.presentation.feature.auth.login.LoginScreen
import project.collab.banksampah.presentation.feature.auth.register.RegisterScreen
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.RedeemTrashHistoryScreen
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.HistoryRedeemPointScreen
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.state.HistoryRedeemPointResponse
import project.collab.banksampah.presentation.feature.profile.redeempoint.RedeemPointScreen
import project.collab.banksampah.presentation.feature.profile.user.ProfileUserScreen
import project.collab.banksampah.presentation.navigation.route.NavRoute

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home
    ) {
        navigation<NavRoute.Home>(
            startDestination = NavRoute.Home.Lobby
        ) {
            composable<NavRoute.Home.Lobby> {
                HomeBottomSheetHost(
                    mainNavController = navController
                )
            }
        }

        navigation<NavRoute.Auth>(
            startDestination = NavRoute.Auth.Login
        ) {
            composable<NavRoute.Auth.Login> {
                LoginScreen(
                    onLoginClick = {},
                    onGoToRegisterClick = {},
                    onForgotPasswordClick = {},
                    onBackClick = navController::navigateUp
                )
            }
            composable<NavRoute.Auth.Register> {
                RegisterScreen(
                    onRegisterClick = {},
                    onGoToLoginClick = {},
                    onBackClick = navController::navigateUp
                )
            }
        }

        composable<NavRoute.Profile.UserProfile> {
            ProfileUserScreen(
                onBackClick = navController::navigateUp
            )
        }
        composable<NavRoute.Profile.RedeemPoint> {
            RedeemPointScreen {

            }
        }
        composable<NavRoute.Profile.HistoryRedeemPoint> {
            HistoryRedeemPointScreen(
                onBackClick = navController::navigateUp,
                historyRedeemPointResponse = getHistoryRedeemPointResponse()
            )
        }
        composable<NavRoute.Profile.HistoryRedeemTrash> {
            RedeemTrashHistoryScreen(
                onBackClick = navController::navigateUp,
                redeemTrashHistoryResponse = getRedeemTrashHistoryResponse()
            )
        }
    }
}