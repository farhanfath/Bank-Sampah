package project.collab.banksampah.domain.model.response.auth

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val token: String
)