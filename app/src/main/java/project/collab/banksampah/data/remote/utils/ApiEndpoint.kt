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

    object Gallery {
        const val ALL = "/gallery/getAllGallery"
        const val BY_ID = "/gallery/getGalleryById/{id}"
    }

    object User {
        const val BY_ID = "/user/getUserById/{id}"
        const val EDIT_USER = ""
        const val CHANGE_PASSWORD = ""
        const val ALL_USER_TRASH_EXCHANGE_HISTORY = ""
        const val USER_TRASH_EXHCANGE_HISTORY_BY_ID = ""
        const val REQUEST_POINT_EXCHANGE = ""
    }
}