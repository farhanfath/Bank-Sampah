package project.collab.banksampah.domain.model.request

data class LoginRequest(
    val phoneNumber: String = "",
    val password: String = ""
)