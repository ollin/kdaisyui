package io.github.ollin.kdaisyui.docsnippets

/** One Markdown document at [path], relative to the repository, and the sources of its snippets. */
class SnippetDocument(private val path: String, private val markdown: String) {
    val sources: List<SnippetSource> get() = kotlinFencesIn(markdown).map { SnippetSource(path, it) }
}
