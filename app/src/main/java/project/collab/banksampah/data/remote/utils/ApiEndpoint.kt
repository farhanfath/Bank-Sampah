package project.collab.banksampah.data.remote.utils

object ApiEndpoint {

    object Auth {
        const val REGISTER = "/user/registerUser"
        const val LOGIN = "/user/loginUser"
    }

    object Article {
        const val ALL = "/article/getAllArticel"
        const val BY_ID = "/article/getArticleById/{id}"
    }
}