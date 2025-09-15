package project.collab.banksampah.presentation.feature.profile.historyredeempoint.state

data class HistoryRedeemPointResponse(
    val historyRedeemPointData : List<HistoryRedeemPointData>
)

data class HistoryRedeemPointData(
    val pointRequest: String,
    val redeemStatus: RedeemStatus
)

enum class RedeemStatus(
    val status: String,
    val desc: String
) {
    DENIED("denied","Ditolak"),
    PENDING("pending","Belum Diverifikasi"),
    SUCCESS("approved","Diterima")
}