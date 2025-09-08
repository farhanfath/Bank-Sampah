package project.collab.banksampah.domain.model.request

data class RegisterRequest(
    val name: String = "",
    val nik: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val bsuBranchName: String = "",
    val bsuBranchCode: String = "",
    val password: String = ""
)