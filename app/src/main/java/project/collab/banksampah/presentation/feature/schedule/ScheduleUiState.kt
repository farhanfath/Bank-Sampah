package project.collab.banksampah.presentation.feature.schedule

data class ScheduleUiState(
    val searchQuery: String = "",
    val selectedStatus: ScheduleStatus = ScheduleStatus.ALL
)

enum class ScheduleStatus(val displayName: String) {
    ALL("Semua"),
    OPEN("Buka"),
    CLOSED("Tutup"),
    UPCOMING("Akan Datang")
}