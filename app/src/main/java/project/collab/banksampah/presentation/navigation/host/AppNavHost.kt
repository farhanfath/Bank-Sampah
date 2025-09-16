package project.collab.banksampah.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import project.collab.banksampah.presentation.feature.article_detail.ArticleDetailScreen
import project.collab.banksampah.presentation.feature.article_detail.SmartScrollArticleDetailScreen
import project.collab.banksampah.presentation.feature.auth.login.LoginScreen
import project.collab.banksampah.presentation.feature.auth.register.RegisterScreen
import project.collab.banksampah.presentation.feature.profile.historyRedeemTrash.RedeemTrashHistoryScreen
import project.collab.banksampah.presentation.feature.profile.historyredeempoint.RedeemPointHistoryScreen
import project.collab.banksampah.presentation.feature.profile.redeempoint.RedeemPointScreen
import project.collab.banksampah.presentation.feature.profile.user.ProfileUserScreen
import project.collab.banksampah.presentation.navigation.extensions.navigateToLogin
import project.collab.banksampah.presentation.navigation.extensions.navigateToRedeemPointHistory
import project.collab.banksampah.presentation.navigation.extensions.navigateToRegister
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

        composable<NavRoute.Auth.Login> {
            LoginScreen(
                onLoginSuccess = navController::navigateUp,
                onGoToRegisterClick = navController::navigateToRegister,
                onForgotPasswordClick = {},
                onBackClick = navController::navigateUp
            )
        }

        composable<NavRoute.Auth.Register> {
            RegisterScreen(
                onRegisterSuccess = navController::navigateUp,
                onGoToLoginClick = navController::navigateToLogin,
                onBackClick = navController::navigateUp
            )
        }

        composable<NavRoute.Profile.UserProfile> {
            ProfileUserScreen(
                onBackClick = navController::navigateUp
            )
        }
        composable<NavRoute.Profile.RedeemPoint> {
            RedeemPointScreen(
                onBackClick = navController::navigateUp,
                onNavigateToRedeemPointHistory = navController::navigateToRedeemPointHistory
            )
        }
        composable<NavRoute.Profile.HistoryRedeemPoint> {
            RedeemPointHistoryScreen(
                onBackClick = navController::navigateUp,
            )
        }
        composable<NavRoute.Profile.HistoryRedeemTrash> {
            RedeemTrashHistoryScreen(
                onBackClick = navController::navigateUp
            )
        }
        composable<NavRoute.Detail.ArticleDetail> { entry ->
            val articleDetail = entry.toRoute<NavRoute.Detail.ArticleDetail>()
//            ArticleDetailScreen(
//                onBackClick = navController::navigateUp,
//                articleId = articleDetail.articleId,
//                title = articleDetail.title,
//                description = articleDetail.description,
//                imageUrl = articleDetail.imageUrl,
//                timeStamp = articleDetail.timeStamp,
//                editor = articleDetail.editor
//            )
            SmartScrollArticleDetailScreen(
                onBackClick = navController::navigateUp,
                articleId = articleDetail.articleId,
                title = articleDetail.title,
                description = articleDetail.description,
                imageUrl = articleDetail.imageUrl,
                timeStamp = articleDetail.timeStamp
            )
        }
    }
}