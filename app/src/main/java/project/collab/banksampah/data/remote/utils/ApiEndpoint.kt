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

    object TypeTrash {
        const val ALL = "/trash/getAllTypeOfTrash"
        const val BY_ID = "/trash/getTypeOfTrashById/{id}"
    }

    object BSU {
        const val ALL = "/bsu/getAllBSU"
        const val BY_ID = "/bsu/getBSUById/{id}"
    }

    object Schedule {
        const val ALL = "/schedule/getAllSchedule"
        const val BY_ID = "/schedule/getScheduleById/{id}"
    }

    object User {
        const val BY_ID = "/user/getUserById/{id}"
        const val EDIT_USER = "/user/editUser/{id}"
        const val CHANGE_PASSWORD = "/user/changePasswordUser/{userId}"
        const val ALL_USER_TRASH_EXCHANGE_HISTORY = "/user/getUserTrashExchangeHistory/{userId}"
        const val USER_TRASH_EXCHANGE_HISTORY_BY_ID = "/user/getUserTrashExchangeHistory/{userId}/{trashExchangeId}"
        const val REQUEST_POINT_EXCHANGE = "/point/request"
        const val ALL_USER_POINT_REDEEM_HISTORY = "/user/getUserPointTransactionHistory/{userId}"
    }
}