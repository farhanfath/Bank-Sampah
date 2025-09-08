package project.collab.banksampah.domain.model.request

// usage for reset password from profile
data class ResetPassRequest(
    val newPassword: String = "",
    val confirmPassword: String = ""
)