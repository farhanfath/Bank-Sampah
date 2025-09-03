package project.collab.banksampah.presentation.navigation.extensions

import androidx.navigation.NavHostController
import project.collab.banksampah.presentation.navigation.navigator.AuthNavigator
import project.collab.banksampah.presentation.navigation.navigator.HomeProfileNavigator
import project.collab.banksampah.presentation.navigation.route.NavRoute

/**
 * Profile navigation
 */
fun NavHostController.navigateToUserProfile() = navigate(NavRoute.Profile.UserProfile)
fun NavHostController.navigateToRedeemPoint() = navigate(NavRoute.Profile.RedeemPoint)
fun NavHostController.navigateToHistoryRedeemPoint() = navigate(NavRoute.Profile.HistoryRedeemPoint)
fun NavHostController.navigateToHistoryRedeemTrash() = navigate(NavRoute.Profile.HistoryRedeemTrash)

fun NavHostController.asHomeProfileNavigator(): HomeProfileNavigator =
    object : HomeProfileNavigator {
        override fun navigateToUserProfile() = this@asHomeProfileNavigator.navigateToUserProfile()
        override fun navigateToRedeemPoint() = this@asHomeProfileNavigator.navigateToRedeemPoint()
        override fun navigateToHistoryRedeemPoint() = this@asHomeProfileNavigator.navigateToHistoryRedeemPoint()
        override fun navigateToHistoryRedeemTrash() = this@asHomeProfileNavigator.navigateToHistoryRedeemTrash()
    }

/**
 * auth navigation
 */
fun NavHostController.navigateToLogin() = navigate(NavRoute.Auth.Login)
fun NavHostController.navigateToRegister() = navigate(NavRoute.Auth.Register)
fun NavHostController.navigateToForgotPass() = navigate(NavRoute.Auth.ForgotPass)

fun NavHostController.asAuthNavigator() : AuthNavigator =
    object : AuthNavigator {
        override fun navigateToLogin() = this@asAuthNavigator.navigateToLogin()
        override fun navigateToRegister() = this@asAuthNavigator.navigateToRegister()
        override fun navigateToForgotPass() = this@asAuthNavigator.navigateToForgotPass()
    }