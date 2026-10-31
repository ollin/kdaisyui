package io.github.ollin.kdaisyui.docsnippets

/**
 * One Markdown document at [path], relative to the repository, and the sources of its snippets.
 *
 * A tutorial builds one application across its snippets, so each snippet sees what the others
 * declare: the nearest declaration before it, or failing that the nearest after it.
 */
class SnippetDocument(private val path: String, markdown: String) {
    private val fences = kotlinFencesIn(markdown).onEach(::failOnInvalidMarker).filterNot { it.directive is Directive.Skip }

    val sources: List<SnippetSource> get() = fences.map { SnippetSource(path, it, scopeOf(it)) }

    private fun scopeOf(fence: KotlinFence): List<String> =
        (fences.flatMap { it.declaredNames } - fence.declaredNames.toSet()).distinct()
            .map { name -> "snippets.${snippetName(path, declarationOf(name, fence))}.$name" }

    private fun failOnInvalidMarker(fence: KotlinFence) {
        val directive = fence.directive
        if (directive is Directive.Invalid) error("$path:${fence.line}: ${directive.problem}")
    }

    private fun declarationOf(name: String, fence: KotlinFence): KotlinFence {
        val earlier = fences.subList(0, fences.indexOf(fence))
        return earlier.lastOrNull { name in it.declaredNames } ?: fences.first { name in it.declaredNames }
    }
}
