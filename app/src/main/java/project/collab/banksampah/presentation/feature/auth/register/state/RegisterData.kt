package project.collab.banksampah.presentation.feature.auth.register.state

data class RegisterData(
    val name: String = "",
    val nik: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val cabangBSU: String = "",
    val password: String = ""
)