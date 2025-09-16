package project.collab.banksampah.domain.model.request

// user data for edit profile
data class UserRequest(
    val name: String = "",
    val nik: String = "",
    val address: String = ""
)