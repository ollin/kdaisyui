package io.github.ollin.kdaisyui.docsnippets

/** The Kotlin file one snippet compiles as, named for its [document] and the line of its fence. */
class SnippetSource(private val document: String, private val fence: KotlinFence) {
    private val name = document.replace(Regex("\\W"), "_") + "_L${fence.line}"

    val fileName: String get() = "$name.kt"

    val text: String get() = "// $document:${fence.line}\npackage snippets.$name\n\n" +
        defaultImports.joinToString("") { "import $it\n" } + "\n" +
        body + "\n"

    private val body: String get() =
        if (fence.code.lines().any { topLevelDeclaration.containsMatchIn(it) }) fence.code
        else "fun FlowContent.snippet() {\n${fence.code}\n}"
}

private val topLevelDeclaration = Regex("^(fun|suspend fun|val) ")

private val defaultImports = listOf("kotlinx.html.*", "io.github.ollin.kdaisyui.components.*")
