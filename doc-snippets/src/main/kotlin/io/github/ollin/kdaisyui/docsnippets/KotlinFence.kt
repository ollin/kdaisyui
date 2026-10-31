package io.github.ollin.kdaisyui.docsnippets

/** A fenced `kotlin` code block of a Markdown document; [line] is its opening fence, 1-based. */
data class KotlinFence(val line: Int, val code: String)

fun kotlinFencesIn(markdown: String): List<KotlinFence> {
    val lines = markdown.lines()
    return lines.indices.filter { lines[it].startsWith("```kotlin") }
        .map { opening -> KotlinFence(line = opening + 1, code = codeAfter(lines, opening)) }
}

private fun codeAfter(lines: List<String>, opening: Int): String =
    lines.drop(opening + 1).takeWhile { !it.startsWith("```") }.joinToString("\n")
