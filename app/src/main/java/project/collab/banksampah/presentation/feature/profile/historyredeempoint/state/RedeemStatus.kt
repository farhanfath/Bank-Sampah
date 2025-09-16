package project.collab.banksampah.presentation.feature.profile.historyredeempoint.state

import project.collab.banksampah.domain.model.response.point_exchange.RedeemPointHistory

enum class RedeemStatus(
    val status: String,
    val desc: String
) {
    DENIED("denied","Ditolak"),
    PENDING("pending","Belum Diverifikasi"),
    SUCCESS("approved","Diterima");

    companion object {
        fun fromApiStatus(apiStatus: String): RedeemStatus {
            return when (apiStatus.lowercase()) {
                "denied" -> DENIED
                "pending" -> PENDING
                "approved", "success" -> SUCCESS
                else -> PENDING
            }
        }
    }
}

fun RedeemPointHistory.getRedeemStatus(): RedeemStatus {
    return RedeemStatus.fromApiStatus(this.statusPointExchange)
}