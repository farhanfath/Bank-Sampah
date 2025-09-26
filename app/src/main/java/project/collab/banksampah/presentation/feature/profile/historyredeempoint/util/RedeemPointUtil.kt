package project.collab.banksampah.presentation.feature.profile.historyredeempoint.util

import project.collab.banksampah.presentation.feature.profile.PointExchangeStatus

fun getPointStatus(status: String) : PointExchangeStatus {
    return try {
        when (status) {
            "pending" -> PointExchangeStatus.PENDING
            "approved" -> PointExchangeStatus.APPROVED
            "rejected" -> PointExchangeStatus.REJECTED
            else -> PointExchangeStatus.ALL
        }
    } catch (e: Exception) {
        PointExchangeStatus.ALL
    }
}

fun String.toPointExchangeStatus(): PointExchangeStatus {
    return when (this.lowercase()) {
        "pending" -> PointExchangeStatus.PENDING
        "approved" -> PointExchangeStatus.APPROVED
        "rejected" -> PointExchangeStatus.REJECTED
        else -> PointExchangeStatus.ALL
    }
}