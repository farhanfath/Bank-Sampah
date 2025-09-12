package project.collab.banksampah.domain.model

data class User(
    val userId: String,
    val name: String,
    val number: String,
    val nik: String,
    val address: String,
    val bsuBranchName: String,
    val bsuBranchCode: String,
    val totalPointUser: Int,
    val currentPointUser: Int,
    val pointUserAlreadyRedeem: Int,
    val timeStamp: String,
)