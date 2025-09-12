package project.collab.banksampah.presentation.utils

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString

/**
 * date time extensions
 */
fun String.toFormattedTime(): String = DateTimeUtils.formatToTime(this)
fun String.toFormattedDate(): String = DateTimeUtils.formatToDate(this)
fun String.toFormattedDateTime(): String = DateTimeUtils.formatToDateTime(this)
fun String.toFormattedShortDate(): String = DateTimeUtils.formatToShortDate(this)
fun String.toFormattedFullDateTime(): String = DateTimeUtils.formatToFullDateTime(this)
fun String.toRelativeTime(): String = DateTimeUtils.formatToRelativeTime(this)
fun String.toArticleFormat(): String = DateTimeUtils.formatForArticle(this)
fun String.isToday(): Boolean = DateTimeUtils.isToday(this)
fun String.isYesterday(): Boolean = DateTimeUtils.isYesterday(this)
fun String.toTimestamp(): Long = DateTimeUtils.getTimestamp(this)


/**
 * string format extensions
 */
fun String?.toPlainText(): String = HtmlStringUtils.toPlainText(this)
fun String?.toSpanned(): Spanned = HtmlStringUtils.toSpanned(this)
fun String?.toAnnotatedString(): AnnotatedString = HtmlStringUtils.toAnnotatedString(this)
fun String?.toAdvancedAnnotatedString(
    primaryColor: Color = Color.Black,
    linkColor: Color = Color.Blue
): AnnotatedString = HtmlStringUtils.toAdvancedAnnotatedString(this, primaryColor, linkColor)
fun String?.extractExcerpt(wordLimit: Int = 30): String = HtmlStringUtils.extractExcerpt(this, wordLimit)
fun String?.extractFirstParagraph(): String = HtmlStringUtils.extractFirstParagraph(this)
fun String?.extractImageUrls(): List<String> = HtmlStringUtils.extractImageUrls(this)
fun String?.extractLinks(): List<Pair<String, String>> = HtmlStringUtils.extractLinks(this)
fun String?.countWords(): Int = HtmlStringUtils.countWords(this)
fun String?.estimateReadingTime(wordsPerMinute: Int = 200): String = HtmlStringUtils.estimateReadingTime(this, wordsPerMinute)
fun String?.sanitizeHtml(): String = HtmlStringUtils.sanitize(this)
fun String.fromHtml(): Spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)

/**
 * nullable handling
 */

fun String?.replaceIfNull(replacementValue: String = ""): String {
    if (this == null)
        return replacementValue
    return this
}


fun Int?.replaceIfNull(replacementValue: Int = 0): Int {
    if (this == null)
        return replacementValue
    return this
}

fun Boolean?.replaceIfNull(replacementValue: Boolean = false): Boolean {
    if (this == null) return replacementValue
    return this
}
