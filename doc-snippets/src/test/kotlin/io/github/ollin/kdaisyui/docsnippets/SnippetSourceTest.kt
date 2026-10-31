package io.github.ollin.kdaisyui.docsnippets

import kotlin.test.Test
import kotlin.test.assertEquals

class SnippetSourceTest {

    @Test
    fun theFileIsNamedForTheDocumentAndTheLineOfTheFence() {
        val source = SnippetSource("docs/how-to.md", KotlinFence(line = 71, code = ""))
        assertEquals("docs_how_to_md_L71.kt", source.fileName)
    }

    @Test
    fun aStatementIsWrappedInAFlowContentBuilderInItsOwnPackage() {
        val text = textOf("daisyBadge(\"New\")")
        assertEquals("// README.md:9\npackage snippets.README_md_L9", text.lines().take(2).joinToString("\n"))
        assertEquals("fun FlowContent.snippet() {\ndaisyBadge(\"New\")\n}\n", bodyOf(text))
    }

    @Test
    fun aSnippetDeclaringSomethingAtColumnZeroIsNotWrapped() {
        assertEquals("fun FlowContent.card() {\n}\n", bodyOf(textOf("fun FlowContent.card() {\n}")))
    }

    @Test
    fun theSnippetsOwnImportsJoinTheImportBlock() {
        val text = textOf("import a.B\nval b = B()")
        assertEquals("import a.B", importsOf(text).last())
        assertEquals("val b = B()\n", bodyOf(text))
    }

    @Test
    fun kotlinxHtmlAndTheComponentsAreImported() {
        assertEquals(listOf("import kotlinx.html.*", "import io.github.ollin.kdaisyui.components.*"), importsOf(textOf("")))
    }

    @Test
    fun aSnippetMarkedInAReceiverIsWrappedInAnExtensionOnIt() {
        assertEquals("fun Route.snippet() {\nget(\"/\") { }\n}\n", bodyOf(textOf("get(\"/\") { }", marker = "in Route")))
    }

    private fun textOf(code: String, marker: String? = null) = SnippetSource("README.md", KotlinFence(9, code, marker)).text

    private fun importsOf(text: String) = text.lines().filter { it.startsWith("import ") }

    private fun bodyOf(text: String) = text.substringAfterLast("\n\n")
}
