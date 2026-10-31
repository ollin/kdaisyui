package io.github.ollin.kdaisyui.docsnippets

/** A fenced `kotlin` code block of a Markdown document; [line] is its opening fence, 1-based. */
data class KotlinFence(val line: Int)

fun kotlinFencesIn(markdown: String): List<KotlinFence> {
    val lines = markdown.lines()
    return lines.indices.filter { lines[it].startsWith("```kotlin") }.map { KotlinFence(line = it + 1) }
}
