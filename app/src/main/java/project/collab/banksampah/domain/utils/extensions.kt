package project.collab.banksampah.domain.utils

fun isValidPhoneNumber(number: String): Boolean {
    val phoneRegex = "^(\\+62|62|0)8[1-9][0-9]{6,9}$".toRegex()
    return phoneRegex.matches(number)
}