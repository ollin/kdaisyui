package io.github.ollin.kdaisyui.docsnippets

/** The Kotlin file one snippet compiles as, named for its [document] and the line of its fence. */
class SnippetSource(private val document: String, private val fence: KotlinFence) {
    private val name = document.replace(Regex("\\W"), "_") + "_L${fence.line}"

    val fileName: String get() = "$name.kt"

    val text: String get() = "// $document:${fence.line}\npackage snippets.$name\n\n" +
        imports.joinToString("") { "$it\n" } + "\n" +
        body + "\n"

    private val lines = fence.code.lines().partition { it.startsWith("import ") }

    private val imports: List<String> get() = defaultImports.map { "import $it" } + lines.first

    private val code: String get() = lines.second.joinToString("\n")

    private val body: String get() =
        if (code.lines().any { topLevelDeclaration.containsMatchIn(it) }) code
        else "fun ${(fence.marker ?: "in FlowContent").removePrefix("in ")}.snippet() {\n$code\n}"
}

private val topLevelDeclaration = Regex("^(fun|suspend fun|val) ")

private val defaultImports = listOf("kotlinx.html.*", "io.github.ollin.kdaisyui.components.*")
