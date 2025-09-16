package project.collab.banksampah.presentation.navigation.route

import kotlinx.serialization.Serializable
import project.collab.banksampah.domain.model.response.article.Article

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

    @Serializable
    object Detail : NavRoute("Detail") {

        @Serializable
        data class ArticleDetail(
            val articleId: String,
            val title: String,
            val description: String,
            val imageUrl: String,
            val timeStamp: String,
            val editor: String
        ) : NavRoute("DetailArticle")

        @Serializable
        object Search : NavRoute("DetailSearch")
    }
}