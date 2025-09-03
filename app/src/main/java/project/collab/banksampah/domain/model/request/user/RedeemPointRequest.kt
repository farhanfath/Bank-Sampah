package project.collab.banksampah.domain.model.request.user

data class RedeemPointRequest(
    val pointToRedeem: String = "",
    val cabangBSU: String = ""
)