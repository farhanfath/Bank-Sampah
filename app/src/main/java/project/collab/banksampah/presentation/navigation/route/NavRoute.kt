package project.collab.banksampah.presentation.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute(val route: String) {

    @Serializable
    object Auth : NavRoute("Auth") {

        @Serializable
        object Login : NavRoute("AuthLogin")

        @Serializable
        object Register : NavRoute("AuthRegister")

        @Serializable
        object ForgotPass: NavRoute("AuthForgotPass")
    }

    /**
     * Bottom Navigation Route
     */
    @Serializable
    object Home : NavRoute("Home") {

        @Serializable
        object Lobby : NavRoute("HomeLobby")

        @Serializable
        object Gallery : NavRoute("HomeGallery")

        @Serializable
        object TypeTrash : NavRoute("HomeTypeTrash")

        @Serializable
        object Schedule : NavRoute("HomeSchedule")

        @Serializable
        object Profile : NavRoute("HomeProfile")

        @Serializable
        object Forum : NavRoute("HomeForum")
    }

    @Serializable
    object Profile : NavRoute("Profile") {

        @Serializable
        object UserProfile : NavRoute("UserProfile")

        @Serializable
        object RedeemPoint : NavRoute("RedeemPoint")

        @Serializable
        object HistoryRedeemPoint : NavRoute("HistoryRedeemPoint")

        @Serializable
        object HistoryRedeemTrash : NavRoute("HistoryRedeemTrash")
    }
}