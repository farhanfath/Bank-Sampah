package project.collab.banksampah.presentation.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object Dashboard : NavRoute()

    /**
     * Bottom Navigation Route
     */
    @Serializable
    object Home : NavRoute()

    @Serializable
    object Gallery : NavRoute()

    // section jenis sampah
    @Serializable
    object TypeTrash : NavRoute()

    @Serializable
    object Schedule : NavRoute()

    @Serializable
    object Profile : NavRoute()

    @Serializable
    object Forum : NavRoute()
}

@Serializable
sealed class AuthRoute {

    @Serializable
    object Login : AuthRoute()

    @Serializable
    object Register : AuthRoute()

    @Serializable
    object ForgotPass: AuthRoute()
}