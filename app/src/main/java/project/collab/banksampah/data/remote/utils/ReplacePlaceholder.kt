package project.collab.banksampah.data.remote.utils

fun String.replacePlaceholders(vararg pairs: Pair<String, String>): String {
    var result = this
    pairs.forEach { (key, value) ->
        result = result.replace("{$key}", value)
    }
    return result
}