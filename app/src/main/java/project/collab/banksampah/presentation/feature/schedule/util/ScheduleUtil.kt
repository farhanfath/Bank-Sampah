package project.collab.banksampah.presentation.feature.schedule.util

import project.collab.banksampah.presentation.feature.schedule.ScheduleStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getScheduleStatus(openAt: String, closeAt: String): ScheduleStatus {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val now = Date()
        val openTime = inputFormat.parse(openAt)
        val closeTime = inputFormat.parse(closeAt)

        when {
            now.before(openTime) -> ScheduleStatus.UPCOMING
            now.after(closeTime) -> ScheduleStatus.CLOSED
            else -> ScheduleStatus.OPEN
        }
    } catch (e: Exception) {
        ScheduleStatus.ALL
    }
}

fun getTimeRemaining(targetTime: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val now = Date()
        val target = inputFormat.parse(targetTime)

        val diff = target!!.time - now.time
        val hours = diff / (1000 * 60 * 60)
        val minutes = (diff % (1000 * 60 * 60)) / (1000 * 60)

        when {
            diff < 0 -> "Sudah lewat"
            hours > 24 -> "${hours / 24} hari lagi"
            hours > 0 -> "${hours}j ${minutes}m lagi"
            else -> "${minutes}m lagi"
        }
    } catch (e: Exception) {
        ""
    }
}