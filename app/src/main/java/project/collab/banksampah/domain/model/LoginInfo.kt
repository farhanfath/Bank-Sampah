package project.collab.banksampah.domain.model

data class LoginInfo(
    val phoneNumber: String,
    val token: String,
    val userId: String
)