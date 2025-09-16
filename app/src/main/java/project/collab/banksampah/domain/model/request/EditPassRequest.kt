package project.collab.banksampah.domain.model.request

// usage for reset password from profile
data class EditPassRequest(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)