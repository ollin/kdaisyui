package io.github.ollin.kdaisyui.docsnippets

/** A fenced `kotlin` code block of a Markdown document; [line] is its opening fence, 1-based. */
data class KotlinFence(val line: Int, val code: String, val marker: String? = null) {
    /** The functions and properties this snippet declares at top level; inside a receiver it declares none. */
    val declaredNames: List<String> get() =
        if (directive is Directive.In) emptyList() else code.lines().mapNotNull { declaration.find(it)?.groupValues[1] }

    /** What the marker above the fence asks of it. */
    val directive: Directive get() = directiveOf(marker)
}

private val declaration = Regex("^(?:suspend )?(?:fun (?:\\S+\\.)?|val )(\\w+)")

fun kotlinFencesIn(markdown: String): List<KotlinFence> {
    val lines = markdown.lines()
    return lines.indices.filter { lines[it].startsWith("```kotlin") }
        .map { opening -> KotlinFence(opening + 1, codeAfter(lines, opening), markerAbove(lines, opening)) }
}

private fun markerAbove(lines: List<String>, opening: Int): String? =
    lines.getOrNull(opening - 1)?.let { Regex("<!-- snippet: (.*) -->").matchEntire(it) }?.groupValues[1]

private fun codeAfter(lines: List<String>, opening: Int): String =
    lines.drop(opening + 1).takeWhile { !it.startsWith("```") }.joinToString("\n")
