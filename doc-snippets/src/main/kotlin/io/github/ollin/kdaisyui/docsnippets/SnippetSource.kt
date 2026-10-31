package io.github.ollin.kdaisyui.docsnippets

/** The Kotlin file one snippet compiles as, named for its [document] and the line of its fence. */
class SnippetSource(private val document: String, private val fence: KotlinFence, private val scope: List<String> = emptyList()) {
    private val name = snippetName(document, fence)

    val fileName: String get() = "$name.kt"

    val text: String get() = "// $document:${fence.line}\npackage snippets.$name\n\n" +
        imports.joinToString("") { "$it\n" } + "\n" +
        body + "\n"

    private val lines = fence.code.lines().partition { it.startsWith("import ") }

    private val imports: List<String> get() = (defaultImports + scope).map { "import $it" } + lines.first

    private val code: String get() = lines.second.joinToString("\n")

    private val body: String get() =
        if (code.lines().any { topLevelDeclaration.containsMatchIn(it) }) code
        else "fun $receiver.snippet() {\n$code\n}"

    private val receiver: String get() = when (val directive = fence.directive) {
        is Directive.In -> directive.receiver
        else -> "FlowContent"
    }
}

private val topLevelDeclaration = Regex("^(fun|suspend fun|val) ")

private val defaultImports = listOf("kotlinx.html.*", "io.github.ollin.kdaisyui.components.*")

/** The name of a snippet's file and package: its [document] and the line of its [fence]. */
fun snippetName(document: String, fence: KotlinFence): String = document.replace(Regex("\\W"), "_") + "_L${fence.line}"
