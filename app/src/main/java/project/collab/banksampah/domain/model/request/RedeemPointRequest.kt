package project.collab.banksampah.domain.model.request

data class RedeemPointRequest(
    val pointToRedeem: String = "",
    val cabangBSU: String = ""
)