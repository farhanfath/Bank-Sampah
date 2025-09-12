package project.collab.banksampah.presentation.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.graphics.Color
import java.util.regex.Pattern

/**
 * Utility class untuk handle HTML string formatting
 * Mendukung konversi HTML ke plain text, Spanned, dan AnnotatedString untuk Compose
 */
object HtmlStringUtils {

    // Common HTML entities mapping
    private val htmlEntities = mapOf(
        "&nbsp;" to " ",
        "&amp;" to "&",
        "&lt;" to "<",
        "&gt;" to ">",
        "&quot;" to "\"",
        "&#39;" to "'",
        "&apos;" to "'",
        "&cent;" to "¢",
        "&pound;" to "£",
        "&yen;" to "¥",
        "&euro;" to "€",
        "&copy;" to "©",
        "&reg;" to "®",
        "&trade;" to "™",
        "&hellip;" to "…",
        "&mdash;" to "—",
        "&ndash;" to "–",
        "&laquo;" to "«",
        "&raquo;" to "»",
        "&bull;" to "•"
    )

    /**
     * Remove all HTML tags and convert entities to plain text
     * Example: "<p><strong>Hello</strong> world!</p>" -> "Hello world!"
     */
    fun toPlainText(htmlString: String?): String {
        if (htmlString.isNullOrBlank()) return ""

        var text = htmlString

        // Remove HTML tags
        text = text.replace(Regex("<[^>]*>"), "")

        // Replace HTML entities
        htmlEntities.forEach { (entity, replacement) ->
            text = text?.replace(entity, replacement, ignoreCase = true)
        }

        // Replace multiple spaces/newlines with single space
        text = text?.replace(Regex("\\s+"), " ")

        return text?.trim() ?: ""
    }

    /**
     * Convert HTML to Android Spanned (for TextView)
     * Preserves basic formatting like bold, italic, etc.
     */
    fun toSpanned(htmlString: String?): Spanned {
        if (htmlString.isNullOrBlank()) return Html.fromHtml("", Html.FROM_HTML_MODE_COMPACT)

        return Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT)
    }

    /**
     * Convert HTML to AnnotatedString for Jetpack Compose
     * Supports basic formatting: bold, italic, underline, lists
     */
    fun toAnnotatedString(htmlString: String?): AnnotatedString {
        if (htmlString.isNullOrBlank()) return AnnotatedString("")

        return buildAnnotatedString {
            var text = htmlString

            // Handle line breaks
            text = text.replace("<br>", "\n")
                .replace("<br/>", "\n")
                .replace("<br />", "\n")

            // Handle paragraphs
            text = text.replace("<p>", "")
                .replace("</p>", "\n\n")

            // Extract and apply formatting
            val boldRegex = Regex("<strong>(.*?)</strong>", RegexOption.DOT_MATCHES_ALL)
            val italicRegex = Regex("<em>(.*?)</em>", RegexOption.DOT_MATCHES_ALL)
            val underlineRegex = Regex("<u>(.*?)</u>", RegexOption.DOT_MATCHES_ALL)

            // Simple approach: extract text and apply basic formatting
            val cleanText = toPlainText(text)
            append(cleanText)

            // Apply bold formatting
            boldRegex.findAll(htmlString).forEach { match ->
                val startIndex = cleanText.indexOf(toPlainText(match.groupValues[1]))
                if (startIndex >= 0) {
                    val endIndex = startIndex + toPlainText(match.groupValues[1]).length
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), startIndex, endIndex)
                }
            }

            // Apply italic formatting
            italicRegex.findAll(htmlString).forEach { match ->
                val startIndex = cleanText.indexOf(toPlainText(match.groupValues[1]))
                if (startIndex >= 0) {
                    val endIndex = startIndex + toPlainText(match.groupValues[1]).length
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), startIndex, endIndex)
                }
            }
        }
    }

    /**
     * Extract first N words from HTML content
     * Useful for previews/excerpts
     */
    fun extractExcerpt(htmlString: String?, wordLimit: Int = 30): String {
        val plainText = toPlainText(htmlString)
        val words = plainText.split(Regex("\\s+"))

        return if (words.size <= wordLimit) {
            plainText
        } else {
            words.take(wordLimit).joinToString(" ") + "..."
        }
    }

    /**
     * Extract first paragraph from HTML content
     */
    fun extractFirstParagraph(htmlString: String?): String {
        if (htmlString.isNullOrBlank()) return ""

        val paragraphRegex = Regex("<p>(.*?)</p>", RegexOption.DOT_MATCHES_ALL)
        val match = paragraphRegex.find(htmlString)

        return if (match != null) {
            toPlainText(match.groupValues[1])
        } else {
            extractExcerpt(htmlString, 50)
        }
    }

    /**
     * Extract all images URLs from HTML content
     */
    fun extractImageUrls(htmlString: String?): List<String> {
        if (htmlString.isNullOrBlank()) return emptyList()

        val imageRegex = Regex("<img[^>]+src=\"([^\"]+)\"", RegexOption.IGNORE_CASE)
        return imageRegex.findAll(htmlString)
            .map { it.groupValues[1] }
            .toList()
    }

    /**
     * Extract all links from HTML content
     */
    fun extractLinks(htmlString: String?): List<Pair<String, String>> {
        if (htmlString.isNullOrBlank()) return emptyList()

        val linkRegex = Regex("<a[^>]+href=\"([^\"]+)\"[^>]*>(.*?)</a>", RegexOption.DOT_MATCHES_ALL)
        return linkRegex.findAll(htmlString)
            .map { Pair(it.groupValues[1], toPlainText(it.groupValues[2])) }
            .toList()
    }

    /**
     * Count words in HTML content (excluding tags)
     */
    fun countWords(htmlString: String?): Int {
        val plainText = toPlainText(htmlString)
        return if (plainText.isBlank()) 0 else plainText.split(Regex("\\s+")).size
    }

    /**
     * Estimate reading time based on word count
     * Average reading speed: 200 words per minute
     */
    fun estimateReadingTime(htmlString: String?, wordsPerMinute: Int = 200): String {
        val wordCount = countWords(htmlString)
        val minutes = (wordCount / wordsPerMinute).coerceAtLeast(1)

        return when {
            minutes < 1 -> "< 1 menit"
            minutes == 1 -> "1 menit"
            else -> "$minutes menit"
        }
    }

    /**
     * Clean HTML for safe display (remove potentially dangerous tags)
     */
    fun sanitize(htmlString: String?): String {
        if (htmlString.isNullOrBlank()) return ""

        var cleaned = htmlString

        // Remove script tags
        cleaned = cleaned.replace(Regex("<script[^>]*>.*?</script>", RegexOption.DOT_MATCHES_ALL), "")

        // Remove style tags
        cleaned = cleaned.replace(Regex("<style[^>]*>.*?</style>", RegexOption.DOT_MATCHES_ALL), "")

        // Remove dangerous attributes
        cleaned = cleaned.replace(Regex("on\\w+=\"[^\"]*\"", RegexOption.IGNORE_CASE), "")
        cleaned = cleaned.replace(Regex("javascript:", RegexOption.IGNORE_CASE), "")

        return cleaned
    }

    /**
     * Convert lists to readable format
     */
    fun formatLists(htmlString: String?): String {
        if (htmlString.isNullOrBlank()) return ""

        var text = htmlString

        // Handle ordered lists
        text = text.replace(Regex("<ol[^>]*>"), "")
            .replace("</ol>", "\n")
            .replace(Regex("<li[^>]*>"), "• ")
            .replace("</li>", "\n")

        // Handle unordered lists
        text = text.replace(Regex("<ul[^>]*>"), "")
            .replace("</ul>", "\n")

        return text
    }

    /**
     * Advanced HTML to AnnotatedString with better formatting support
     */
    fun toAdvancedAnnotatedString(
        htmlString: String?,
        primaryColor: Color = Color.Black,
        linkColor: Color = Color.Blue
    ): AnnotatedString {
        if (htmlString.isNullOrBlank()) return AnnotatedString("")

        return buildAnnotatedString {
            // Pre-process HTML
            var processedHtml = htmlString

            // Convert lists to bullet points
            processedHtml = formatLists(processedHtml)

            // Handle line breaks and paragraphs
            processedHtml = processedHtml
                .replace("<br>", "\n")
                .replace("<br/>", "\n")
                .replace("<br />", "\n")
                .replace("<p>", "")
                .replace("</p>", "\n\n")
                .replace("<div>", "")
                .replace("</div>", "\n")

            // Get clean text
            val plainText = toPlainText(processedHtml)
            append(plainText)

            // Apply formatting styles
            applyHtmlStyles(htmlString, plainText, primaryColor, linkColor)
        }
    }

    private fun AnnotatedString.Builder.applyHtmlStyles(
        originalHtml: String,
        plainText: String,
        primaryColor: Color,
        linkColor: Color
    ) {
        // Bold text
        findAndApplyStyle(originalHtml, plainText, "<strong>(.*?)</strong>") {
            SpanStyle(fontWeight = FontWeight.Bold)
        }

        // Italic text
        findAndApplyStyle(originalHtml, plainText, "<em>(.*?)</em>") {
            SpanStyle(fontStyle = FontStyle.Italic)
        }

        // Underline text
        findAndApplyStyle(originalHtml, plainText, "<u>(.*?)</u>") {
            SpanStyle(textDecoration = TextDecoration.Underline)
        }

        // Links
        findAndApplyStyle(originalHtml, plainText, "<a[^>]*>(.*?)</a>") {
            SpanStyle(color = linkColor, textDecoration = TextDecoration.Underline)
        }
    }

    private fun AnnotatedString.Builder.findAndApplyStyle(
        originalHtml: String,
        plainText: String,
        pattern: String,
        styleProvider: () -> SpanStyle
    ) {
        val regex = Regex(pattern, RegexOption.DOT_MATCHES_ALL)
        regex.findAll(originalHtml).forEach { match ->
            val content = toPlainText(match.groupValues[1])
            val startIndex = plainText.indexOf(content)
            if (startIndex >= 0) {
                val endIndex = startIndex + content.length
                addStyle(styleProvider(), startIndex, endIndex)
            }
        }
    }
}