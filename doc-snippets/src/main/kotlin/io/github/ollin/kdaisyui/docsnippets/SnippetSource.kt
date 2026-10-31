package io.github.ollin.kdaisyui.docsnippets

/** The Kotlin file one snippet compiles as, named for its [document] and the line of its fence. */
class SnippetSource(document: String, fence: KotlinFence) {
    private val name = document.replace(Regex("\\W"), "_") + "_L${fence.line}"

    val fileName: String get() = "$name.kt"
}
