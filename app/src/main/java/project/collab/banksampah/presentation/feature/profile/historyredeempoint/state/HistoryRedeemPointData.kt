package project.collab.banksampah.presentation.feature.profile.historyredeempoint.state

data class HistoryRedeemPointResponse(
    val historyRedeemPointData : List<HistoryRedeemPointData>
)

data class HistoryRedeemPointData(
    val pointRequest: String,
    val redeemStatus: RedeemStatus
)

enum class RedeemStatus(
    val desc: String
) {
    DENIED("Ditolak"),
    PENDING("Belum Diverifikasi"),
    SUCCESS("Diterima")
}