package io.github.ollin.kdaisyui.docsnippets

/**
 * One Markdown document at [path], relative to the repository, and the sources of its snippets.
 *
 * A tutorial builds one application across its snippets, so each snippet sees what the others
 * declare: the nearest declaration before it, or failing that the nearest after it.
 */
class SnippetDocument(private val path: String, markdown: String) {
    private val fences = kotlinFencesIn(markdown)

    val sources: List<SnippetSource> get() = fences.map { SnippetSource(path, it, scopeOf(it)) }

    private fun scopeOf(fence: KotlinFence): List<String> =
        (fences.flatMap { it.declaredNames } - fence.declaredNames.toSet()).distinct()
            .map { name -> "snippets.${snippetName(path, declarationOf(name, fence))}.$name" }

    private fun declarationOf(name: String, fence: KotlinFence): KotlinFence {
        val declaring = fences.filter { name in it.declaredNames }
        return declaring.lastOrNull { it.line < fence.line } ?: declaring.first()
    }
}
