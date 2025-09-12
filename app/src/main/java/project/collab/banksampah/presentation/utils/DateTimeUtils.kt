package project.collab.banksampah.presentation.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * Utility class untuk handle formatting datetime
 * Support format ISO 8601 dari backend (2025-08-14T03:11:12.067Z)
 * Compatible dengan Android API level 21+ (dengan fallback untuk pre-API 26)
 */
object DateTimeUtils {

    // Format patterns
    private const val ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    /**
     * Parse ISO string ke Date (untuk pre-API 26) atau ZonedDateTime (API 26+)
     */
    private fun parseIsoString(isoString: String): Any? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ZonedDateTime.parse(isoString)
            } else {
                val format = SimpleDateFormat(ISO_PATTERN, Locale.getDefault())
                format.timeZone = TimeZone.getTimeZone("UTC")
                format.parse(isoString)
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Format ke jam saja (HH:mm)
     * Example: "03:11"
     */
    fun formatToTime(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid time"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale("id", "ID"))
            (parsed as ZonedDateTime).format(formatter)
        } else {
            val formatter = SimpleDateFormat("HH:mm", Locale("id", "ID"))
            formatter.format(parsed as Date)
        }
    }

    /**
     * Format ke tanggal saja (dd MMMM yyyy)
     * Example: "14 Agustus 2025"
     */
    fun formatToDate(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid date"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id", "ID"))
            (parsed as ZonedDateTime).format(formatter)
        } else {
            val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
            formatter.format(parsed as Date)
        }
    }

    /**
     * Format ke tanggal dan jam (dd MMMM yyyy, HH:mm)
     * Example: "14 Agustus 2025, 03:11"
     */
    fun formatToDateTime(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid datetime"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm", Locale("id", "ID"))
            (parsed as ZonedDateTime).format(formatter)
        } else {
            val formatter = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale("id", "ID"))
            formatter.format(parsed as Date)
        }
    }

    /**
     * Format ke tanggal pendek (dd/MM/yyyy)
     * Example: "14/08/2025"
     */
    fun formatToShortDate(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid date"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("id", "ID"))
            (parsed as ZonedDateTime).format(formatter)
        } else {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
            formatter.format(parsed as Date)
        }
    }

    /**
     * Format ke full datetime dengan hari (EEEE, dd MMMM yyyy 'pukul' HH:mm)
     * Example: "Kamis, 14 Agustus 2025 pukul 03:11"
     */
    fun formatToFullDateTime(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid datetime"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'pukul' HH:mm", Locale("id", "ID"))
            (parsed as ZonedDateTime).format(formatter)
        } else {
            val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy 'pukul' HH:mm", Locale("id", "ID"))
            formatter.format(parsed as Date)
        }
    }

    /**
     * Format relative time (berapa lama yang lalu)
     * Example: "2 jam yang lalu", "3 hari yang lalu"
     */
    fun formatToRelativeTime(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid time"
        val now = System.currentTimeMillis()

        val timestamp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (parsed as ZonedDateTime).toInstant().toEpochMilli()
        } else {
            (parsed as Date).time
        }

        val diffMs = now - timestamp
        val minutes = diffMs / (60 * 1000)
        val hours = diffMs / (60 * 60 * 1000)
        val days = diffMs / (24 * 60 * 60 * 1000)
        val weeks = days / 7
        val months = days / 30
        val years = days / 365

        return when {
            minutes < 1 -> "Baru saja"
            minutes < 60 -> "$minutes menit yang lalu"
            hours < 24 -> "$hours jam yang lalu"
            days < 7 -> "$days hari yang lalu"
            weeks < 4 -> "$weeks minggu yang lalu"
            months < 12 -> "$months bulan yang lalu"
            else -> "$years tahun yang lalu"
        }
    }

    /**
     * Format untuk artikel/berita dengan logika smart
     * - Jika hari ini: "Hari ini, 03:11"
     * - Jika kemarin: "Kemarin, 03:11"
     * - Jika minggu ini: "Senin, 03:11"
     * - Jika lebih lama: "14 Agustus 2025"
     */
    fun formatForArticle(isoString: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid time"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime = parsed as ZonedDateTime
            val now = ZonedDateTime.now()
            val daysDiff = ChronoUnit.DAYS.between(dateTime.toLocalDate(), now.toLocalDate())

            when {
                daysDiff == 0L -> "Hari ini, ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale("id", "ID")))}"
                daysDiff == 1L -> "Kemarin, ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale("id", "ID")))}"
                daysDiff < 7L -> "${dateTime.format(DateTimeFormatter.ofPattern("EEEE", Locale("id", "ID")))}, ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale("id", "ID")))}"
                else -> dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("id", "ID")))
            }
        } else {
            val date = parsed as Date
            val now = Date()
            val calendar = Calendar.getInstance()
            val nowCalendar = Calendar.getInstance()

            calendar.time = date
            nowCalendar.time = now

            val daysDiff = ((nowCalendar.timeInMillis - calendar.timeInMillis) / (24 * 60 * 60 * 1000)).toInt()

            when {
                isSameDay(date, now) -> "Hari ini, ${SimpleDateFormat("HH:mm", Locale("id", "ID")).format(date)}"
                daysDiff == 1 -> "Kemarin, ${SimpleDateFormat("HH:mm", Locale("id", "ID")).format(date)}"
                daysDiff < 7 -> "${SimpleDateFormat("EEEE", Locale("id", "ID")).format(date)}, ${SimpleDateFormat("HH:mm", Locale("id", "ID")).format(date)}"
                else -> SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")).format(date)
            }
        }
    }

    /**
     * Helper function untuk check apakah dua Date adalah hari yang sama (pre-API 26)
     */
    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    /**
     * Check apakah tanggal adalah hari ini
     */
    fun isToday(isoString: String): Boolean {
        val parsed = parseIsoString(isoString) ?: return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime = parsed as ZonedDateTime
            val now = ZonedDateTime.now()
            dateTime.toLocalDate() == now.toLocalDate()
        } else {
            val date = parsed as Date
            val now = Date()
            isSameDay(date, now)
        }
    }

    /**
     * Check apakah tanggal adalah kemarin
     */
    fun isYesterday(isoString: String): Boolean {
        val parsed = parseIsoString(isoString) ?: return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime = parsed as ZonedDateTime
            val now = ZonedDateTime.now()
            ChronoUnit.DAYS.between(dateTime.toLocalDate(), now.toLocalDate()) == 1L
        } else {
            val date = parsed as Date
            val now = Date()
            val daysDiff = ((now.time - date.time) / (24 * 60 * 60 * 1000)).toInt()
            daysDiff == 1
        }
    }

    /**
     * Get timestamp untuk sorting atau comparison
     */
    fun getTimestamp(isoString: String): Long {
        val parsed = parseIsoString(isoString) ?: return 0L

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (parsed as ZonedDateTime).toInstant().toEpochMilli()
        } else {
            (parsed as Date).time
        }
    }

    /**
     * Format custom dengan pattern sendiri
     */
    fun formatCustom(isoString: String, pattern: String): String {
        val parsed = parseIsoString(isoString) ?: return "Invalid datetime"

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val formatter = DateTimeFormatter.ofPattern(pattern, Locale("id", "ID"))
                (parsed as ZonedDateTime).format(formatter)
            } else {
                val formatter = SimpleDateFormat(pattern, Locale("id", "ID"))
                formatter.format(parsed as Date)
            }
        } catch (e: Exception) {
            "Invalid pattern"
        }
    }

    /**
     * Get API level info for debugging
     */
    fun getApiInfo(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            "Using java.time (API ${Build.VERSION.SDK_INT})"
        } else {
            "Using SimpleDateFormat (API ${Build.VERSION.SDK_INT})"
        }
    }
}

