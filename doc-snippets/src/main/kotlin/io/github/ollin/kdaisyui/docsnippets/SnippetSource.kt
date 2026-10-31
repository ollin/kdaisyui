package io.github.ollin.kdaisyui.docsnippets

/** The Kotlin file one snippet compiles as, named for its [document] and the line of its fence. */
class SnippetSource(private val document: String, private val fence: KotlinFence) {
    private val name = document.replace(Regex("\\W"), "_") + "_L${fence.line}"

    val fileName: String get() = "$name.kt"

    val text: String get() = "// $document:${fence.line}\npackage snippets.$name\n\n" +
        "fun FlowContent.snippet() {\n${fence.code}\n}\n"
}
