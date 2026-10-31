package io.github.ollin.kdaisyui.docsnippets

/** A fenced `kotlin` code block of a Markdown document; [line] is its opening fence, 1-based. */
data class KotlinFence(val line: Int, val code: String, val marker: String? = null)

fun kotlinFencesIn(markdown: String): List<KotlinFence> {
    val lines = markdown.lines()
    return lines.indices.filter { lines[it].startsWith("```kotlin") }
        .map { opening -> KotlinFence(opening + 1, codeAfter(lines, opening), markerAbove(lines, opening)) }
}

private fun markerAbove(lines: List<String>, opening: Int): String? =
    lines.getOrNull(opening - 1)?.let { Regex("<!-- snippet: (.*) -->").matchEntire(it) }?.groupValues[1]

private fun codeAfter(lines: List<String>, opening: Int): String =
    lines.drop(opening + 1).takeWhile { !it.startsWith("```") }.joinToString("\n")
